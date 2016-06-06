package com.malsolo.docker.birthday.voting.repository;

import com.malsolo.docker.birthday.voting.domain.VoteData;

public interface VoteDataRepository {

    void rpush(VoteData data);

}
