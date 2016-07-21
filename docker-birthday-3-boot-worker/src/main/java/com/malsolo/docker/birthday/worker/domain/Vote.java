package com.malsolo.docker.birthday.worker.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote implements Serializable {

    private static final long serialVersionUID = -6986413432644716010L;

    private String voterId;

    private String vote;
}
