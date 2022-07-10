package com.study.teamo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TeamoApplication {

  public static void main(String[] args) {
    SpringApplication.run(TeamoApplication.class, args);
  }
}
