package com.study.teamo.service;

import com.study.teamo.dto.user.UserDto;
import com.study.teamo.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCRUDService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public UserDto getUser(Long sysId) {
    return UserDto.from(userRepository.findById(sysId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")));
  }

  @Transactional(readOnly = true)
  public List<UserDto> getUsers() {
    return userRepository.findAll().stream().map(u -> UserDto.from(u)).collect(Collectors.toList());
  }

  public void deleteUser(Long sysId) {
    userRepository.findById(sysId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    userRepository.deleteById(sysId);
  }
}
