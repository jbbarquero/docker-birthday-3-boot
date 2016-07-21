package com.malsolo.docker.birthday.worker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.malsolo.docker.birthday.worker.domain.Vote;

@Repository
public class VoteRepositoryJdbcTemplateImpl implements VoteRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VoteRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Vote vote) {
        this.jdbcTemplate.update("INSERT INTO votes (id, vote) VALUES (?, ?)", vote.getVoterId(), vote.getVote());
    }

}
