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
class CreateBoardDtoTest {

  private final String title = "게시글1";
  private final String content = "내용";
  private Validator validator = null;

  @BeforeEach
  public void setupValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  public CreateBoardDto.CreateBoardDtoBuilder setupBuilder() {
    return CreateBoardDto.builder().title(title).content(content);
  }

  @Test
  @DisplayName("createBoardDto 생성 성공")
  void createBoardDto() {
    // given
    CreateBoardDto createBoardDto = setupBuilder().build();

    // when
    Set<ConstraintViolation<CreateBoardDto>> violations = validator.validate(createBoardDto);

    // then
    Assertions.assertEquals(0, violations.size());
    Assertions.assertEquals(title, createBoardDto.getTitle());
    Assertions.assertEquals(content, createBoardDto.getContent());
  }

  @Test
  @DisplayName("createBoardDto 생성 실패, title null인 경우")
  void createBoardDto_fail_notNull_title() {
    // given
    CreateBoardDto createBoardDto = setupBuilder().title(null).build();

    // when
    Set<ConstraintViolation<CreateBoardDto>> violations = validator.validate(createBoardDto);

    // then
    Assertions.assertEquals(1, violations.size());
    violations.forEach(i -> System.out.println(i.getMessage()));
  }

  @Test
  @DisplayName("createBoardDto 생성 실패, content null인 경우")
  void createBoardDto_fail_notNull_content() {
    // given
    CreateBoardDto createBoardDto = setupBuilder().content(null).build();

    // when
    Set<ConstraintViolation<CreateBoardDto>> violations = validator.validate(createBoardDto);

    // then
    Assertions.assertEquals(1, violations.size());
    violations.forEach(i -> System.out.println(i.getMessage()));
  }
}