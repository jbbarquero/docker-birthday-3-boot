package com.malsolo.docker.birthday.voting.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "options")
@Data
public class Options {

    private String optionA, optionB;

}
