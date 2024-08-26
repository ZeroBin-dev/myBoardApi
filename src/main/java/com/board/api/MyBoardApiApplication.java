package com.board.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.board.api")
@EntityScan(basePackages = "com.board.api")
public class MyBoardApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyBoardApiApplication.class, args);
  }

}
