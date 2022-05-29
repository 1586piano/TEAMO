package com.study.teamo.controller;

import com.study.teamo.dto.user.UserDto;
import com.study.teamo.service.UserCRUDService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRUDController {

  private final UserCRUDService userService;

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
