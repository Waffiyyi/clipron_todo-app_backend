package org.waffiyyidev.clipron_todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClipronTodoappApplication {

   public static void main(String[] args) {
      SpringApplication.run(ClipronTodoappApplication.class, args);
   }

}
