package com.malsolo.docker.birthday.voting.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteData implements Serializable {

    private static final long serialVersionUID = 7771573201492676615L;

    @JsonProperty("voter_id")
    private String voterId;

    private String vote;

}
