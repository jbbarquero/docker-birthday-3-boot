package com.malsolo.docker.birthday.voting.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malsolo.docker.birthday.voting.domain.VoteData;

@Repository
public class VoteDataRepositoryRedisImpl implements VoteDataRepository {

    private static final Logger LOG = LoggerFactory.getLogger(VoteDataRepositoryRedisImpl.class);

    public static final String KEY = "votes";

    private final StringRedisTemplate redisTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public VoteDataRepositoryRedisImpl(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void rpush(VoteData data) {
        Long lengthOfTheListAfterThePushOperation;
        try {

            RedisConnectionFactory connectionFactory = this.redisTemplate.getConnectionFactory();
            if (connectionFactory instanceof JedisConnectionFactory) {
                JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) connectionFactory;
                LOG.warn("Trying to connect to {}:{}", jedisConnectionFactory.getHostName(), jedisConnectionFactory.getPort());
            } else {
                LOG.warn("RedisConnectionFactory class name: " + connectionFactory.getClass().getName());
            }

            lengthOfTheListAfterThePushOperation = this.redisTemplate.opsForList().rightPush(KEY,
                    this.objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            throw new RedisSystemException("Error serializing VoteData: " + e.getMessage(), e);
        }
        LOG.debug("REDIS successfully pushed {}, now the KEY {} has a list of length {}", data, KEY, lengthOfTheListAfterThePushOperation);
    }

}
