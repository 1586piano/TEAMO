package com.study.teamo.domain.auth;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

  private final Long id;
  private final String name;
  private final String auth;

  public UserDetailsImpl(Long id, String name, String auth) {
    this.id = id;
    this.name = name;
    this.auth = auth;
  }

  public static UserDetailsImpl from(User user) {
    return new UserDetailsImpl(user.getId(), user.getName(), user.getAuth());
  }

  public Long getId() {
    return id;
  }

  public String getAuth() {
    return auth;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    ArrayList<GrantedAuthority> auths = new ArrayList<>();
    auths.add(new SimpleGrantedAuthority(auth));
    return auths;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
