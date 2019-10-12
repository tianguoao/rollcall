package com.tga.rollcall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication 
@EnableAsync
@Slf4j
public class RollcallApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RollcallApplication.class);
        application.run(args);
        log.info("********** Rollcall  Server Start Success ! **********");
    }

}
