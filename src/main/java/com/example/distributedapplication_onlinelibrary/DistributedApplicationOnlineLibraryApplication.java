package com.example.distributedapplication_onlinelibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class DistributedApplicationOnlineLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedApplicationOnlineLibraryApplication.class, args);
    }

}
