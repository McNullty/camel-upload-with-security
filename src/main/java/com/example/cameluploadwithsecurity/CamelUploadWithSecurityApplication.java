package com.example.cameluploadwithsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:flowUploadTest.xml")
public class CamelUploadWithSecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(CamelUploadWithSecurityApplication.class, args);
  }

}
