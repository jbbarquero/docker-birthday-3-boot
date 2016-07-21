package com.malsolo.docker.birthday.worker.repository;

import com.malsolo.docker.birthday.worker.domain.Vote;

public interface VoteQueueRepository {

    public Vote bLPop();

}
