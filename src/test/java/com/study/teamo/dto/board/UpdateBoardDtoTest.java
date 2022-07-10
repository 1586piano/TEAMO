package com.study.teamo.dto.board;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UpdateBoardDtoTest {

  private final String title = "게시글1(수정)";
  private final String content = "내용(수정)";
  private Validator validator = null;

  @BeforeEach
  public void setupValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  public UpdateBoardDto.UpdateBoardDtoBuilder setupBuilder() {
    return UpdateBoardDto.builder().title(title).content(content);
  }

  @Test
  @DisplayName("updateBoardDto 생성 성공")
  void updateBoardDto() {
    // given
    UpdateBoardDto updateBoardDto = setupBuilder().build();

    // when
    Set<ConstraintViolation<UpdateBoardDto>> violations = validator.validate(updateBoardDto);

    // then
    Assertions.assertEquals(0, violations.size());
    Assertions.assertEquals(title, updateBoardDto.getTitle());
    Assertions.assertEquals(content, updateBoardDto.getContent());
  }

  @Test
  @DisplayName("updateBoardDto 생성 실패, title null인 경우")
  void updateBoardDto_fail_notNull_title() {
    // given
    UpdateBoardDto updateBoardDto = setupBuilder().title(null).build();

    // when
    Set<ConstraintViolation<UpdateBoardDto>> violations = validator.validate(updateBoardDto);

    // then
    Assertions.assertEquals(1, violations.size());
    violations.forEach(i -> System.out.println(i.getMessage()));
  }

  @Test
  @DisplayName("updateBoardDto 생성 실패, content null인 경우")
  void updateBoardDto_fail_notNull_content() {
    // given
    UpdateBoardDto updateBoardDto = setupBuilder().content(null).build();

    // when
    Set<ConstraintViolation<UpdateBoardDto>> violations = validator.validate(updateBoardDto);

    // then
    Assertions.assertEquals(1, violations.size());
    violations.forEach(i -> System.out.println(i.getMessage()));
  }
}