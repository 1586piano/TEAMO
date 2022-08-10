package com.study.teamo.dto.user;

import com.study.teamo.domain.User;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserDto implements UserDetails {

  private String id;
  private String auth;
  private String password;

  @Builder
  public UserDto(String id, String auth, String password) {
    this.id = id;
    this.auth = auth;
    this.password = password;
  }

  public static UserDto from(User user) {
    return UserDto.builder().id(user.getName()).auth(user.getAuth()).password(user.getPassword())
        .build();
  }

  // 사용자의 권한을 콜렉션 형태로 반환
  // 단, 클래스 자료형은 GrantedAuthority를 구현해야함
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> roles = new HashSet<>(); //권한은 중복될 수 없다.
    for (String role : auth.split(",")) { //"ROLE_ADMIN,ROLE_USER" ADMIN은 USER권한도 가지고 있을 수 있다.
      roles.add(new SimpleGrantedAuthority(role));
    }
    return roles;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return id;
  }

  // 계정 만료 여부 반환
  @Override
  public boolean isAccountNonExpired() {
    // 만료되었는지 확인하는 로직
    return true; // true -> 만료되지 않았음
  }

  // 계정 잠금 여부 반환
  @Override
  public boolean isAccountNonLocked() {
    // 계정 잠금되었는지 확인하는 로직
    return true; // true -> 잠금되지 않았음
  }

  // 패스워드의 만료 여부 반환
  @Override
  public boolean isCredentialsNonExpired() {
    // 패스워드가 만료되었는지 확인하는 로직
    return true; // true -> 만료되지 않았음
  }

  // 계정 사용 가능 여부 반환
  @Override
  public boolean isEnabled() {
    // 계정이 사용 가능한지 확인하는 로직
    return true; // true -> 사용 가능
  }
}
