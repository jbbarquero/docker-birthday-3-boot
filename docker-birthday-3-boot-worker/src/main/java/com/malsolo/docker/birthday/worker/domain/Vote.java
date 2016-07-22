package com.malsolo.docker.birthday.worker.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vote implements Serializable {

    private static final long serialVersionUID = -6986413432644716010L;

    @Id
    @JsonProperty("voter_id")
    private String id;

    private String vote;
}
