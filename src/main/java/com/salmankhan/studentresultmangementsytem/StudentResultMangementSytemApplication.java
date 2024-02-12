package com.salmankhan.studentresultmangementsytem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.salmankhan.studentresultmangementsytem.repository")
@EntityScan("com.salmankhan.studentresultmangementsytem.entity")
public class StudentResultMangementSytemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentResultMangementSytemApplication.class, args);
    }

}
