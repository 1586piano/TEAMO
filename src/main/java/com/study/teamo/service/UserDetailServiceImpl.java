package com.study.teamo.service;

import com.study.teamo.dto.user.UserDto;
import com.study.teamo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl {

  @RequiredArgsConstructor
  @Service
  public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param name 사용자명
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public UserDto loadUserByUsername(String name)
        throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
      return UserDto.from(userRepository.findUserByName(name)
          .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다.")));
    }
  }

}
