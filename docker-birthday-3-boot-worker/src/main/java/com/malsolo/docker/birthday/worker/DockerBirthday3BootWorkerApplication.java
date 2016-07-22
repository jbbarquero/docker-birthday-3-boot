package com.malsolo.docker.birthday.worker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.malsolo.docker.birthday.worker.service.VotePoller;

@SpringBootApplication
@EnableAsync
public class DockerBirthday3BootWorkerApplication {

    @Bean
    CommandLineRunner initPoll(final VotePoller poller) {
        return args -> {
            System.err.println("KKK");
            poller.poll();
            System.err.println("OOO");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(DockerBirthday3BootWorkerApplication.class, args);
    }
}
