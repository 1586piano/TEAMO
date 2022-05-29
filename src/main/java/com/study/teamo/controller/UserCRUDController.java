package com.study.teamo.controller;

import com.study.teamo.dto.user.CreateUserDto;
import com.study.teamo.dto.user.LoginUserDto;
import com.study.teamo.dto.user.UserDto;
import com.study.teamo.service.UserDetailsServiceImpl;
import com.study.teamo.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserDetailsServiceImpl userService;

  @PostMapping("/signup")
  public UserDto createUser(CreateUserDto request) {
    return userService.createUser(request);
  }

  @GetMapping("/login")
  public String login(LoginUserDto request) {
    return userService.login(request);
  }

  @GetMapping("/logout")
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    new SecurityContextLogoutHandler().logout(request, response,
        SecurityContextHolder.getContext().getAuthentication());
    return "logout";
  }

  @GetMapping("/{sysId}")
  public UserDto getUser(@PathVariable("sysId") Long sysId) {
    return userService.getUser(sysId);
  }

  @GetMapping("")
  public List<UserDto> getUsers() {
    return userService.getUsers();
  }

  @DeleteMapping("/{sysId}")
  public void deleteUser(@PathVariable("sysId") Long sysId) {
    userService.deleteUser(sysId);
  }
}
