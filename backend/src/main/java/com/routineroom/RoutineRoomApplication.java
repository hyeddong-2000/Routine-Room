package com.routineroom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.routineroom.mappers")
public class RoutineRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoutineRoomApplication.class, args);
    }
}
