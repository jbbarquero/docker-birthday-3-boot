package com.malsolo.docker.birthday.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malsolo.docker.birthday.worker.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, String> {

}
