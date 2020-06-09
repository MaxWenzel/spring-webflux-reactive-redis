package org.devzone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ReactiveWebfluxSpringDataRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveWebfluxSpringDataRedisApplication.class, args);
    }

}