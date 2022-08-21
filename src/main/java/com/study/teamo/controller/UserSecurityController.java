package com.study.teamo.controller;

import com.study.teamo.domain.auth.User;
import com.study.teamo.dto.user.CreateUserDto;
import com.study.teamo.dto.user.LoginUserDto;
import com.study.teamo.dto.user.UserDto;
import com.study.teamo.repository.UserRepository;
import com.study.teamo.security.JwtTokenProvider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSecurityController {

  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;

  @PostMapping("/signup")
  public UserDto signup(@RequestBody CreateUserDto request) {
    return UserDto.from(userRepository.save(
        User.builder().name(request.getName())
            .password(passwordEncoder.encode(request.getPassword()))
            .auth(
                request.getAuth()).build()));
  }

  @PostMapping("/login")
  public String login(@RequestBody LoginUserDto request) {
    User user = userRepository.findUserByName(request.getName())
        .orElseThrow(() -> new IllegalArgumentException(request.getName()));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("Password not matched");
    }
    return jwtTokenProvider.createToken(user.getName(), user.getAuth());
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    new SecurityContextLogoutHandler().logout(request, response,
        SecurityContextHolder.getContext().getAuthentication());
    return "logout";
  }
}
