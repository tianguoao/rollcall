package com.tga.rollcall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication 
@EnableAutoConfiguration
@EnableWebMvc
@EnableAsync
@EnableTransactionManagement(proxyTargetClass = true)
@ServletComponentScan
@Slf4j
public class RollcallApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RollcallApplication.class);
        application.run(args);
        log.info("********** Rollcall  Server Start Success ! **********");
    }

}
