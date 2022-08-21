package com.study.teamo.service;

import com.study.teamo.domain.auth.User;
import com.study.teamo.domain.auth.UserDetailsImpl;
import com.study.teamo.repository.UserRepository;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService, Serializable {

  private final UserRepository userRepository;

  /**
   * Spring Security 필수 메소드 구현
   *
   * @param name 사용자명
   * @return UserDetails
   * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
   */
  @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
  public UserDetails loadUserByUsername(String name)
      throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
    return UserDetailsImpl.from(userRepository.findUserByName(name)
        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다.")));
  }

  /**
   * 현재 사용자 정보를 반환하는 메소
   *
   * @return UserDetailsImpl
   */
  public UserDetailsImpl getCurrentUserDetails() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    return userDetails;
  }

  /**
   * 현재 사용자를 반환하는 메소드
   *
   * @return User
   * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
   */
  @Transactional(readOnly = true)
  public User getCurrentUser() {
    return userRepository.findById(getCurrentUserDetails().getId())
        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
  }
}
