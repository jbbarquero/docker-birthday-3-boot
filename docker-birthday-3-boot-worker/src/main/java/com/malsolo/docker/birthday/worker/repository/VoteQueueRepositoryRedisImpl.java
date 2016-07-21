package com.malsolo.docker.birthday.worker.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malsolo.docker.birthday.worker.domain.Vote;

@Repository
public class VoteQueueRepositoryRedisImpl implements VoteQueueRepository {

    private final StringRedisTemplate redisTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public VoteQueueRepositoryRedisImpl(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        super();
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Vote bLPop() {
        try {
            return objectMapper.readValue(this.redisTemplate.getConnectionFactory().getConnection().bLPop(0, "votes".getBytes()).get(0),
                    Vote.class);
        } catch (IOException e) {
            throw new RedisSystemException("Error obtaining vote: " + e.getMessage(), e);
        }
    }

}
