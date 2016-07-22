package com.malsolo.docker.birthday.worker.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.malsolo.docker.birthday.worker.domain.Vote;
import com.malsolo.docker.birthday.worker.repository.VoteQueueRepository;
import com.malsolo.docker.birthday.worker.repository.VoteRepository;

@Service
public class VotePoller {

    private static final Logger LOG = LoggerFactory.getLogger(VotePoller.class);

    private final VoteQueueRepository voteQueueRepository;

    private final VoteRepository voteRepository;

    @Value("${voter.poll}")
    private boolean voterPoll;

    @Autowired
    public VotePoller(VoteQueueRepository voteQueueRepository, VoteRepository voteRepository) {
        super();
        this.voteQueueRepository = voteQueueRepository;
        this.voteRepository = voteRepository;
    }

    @Async
    public void poll() throws JsonParseException, JsonMappingException, IOException {

        LOG.info("Polling...");

        while (voterPoll) {
            LOG.info("Polling vote...");
            Vote vote = this.voteQueueRepository.bLPop();
            LOG.info("Obtained vote: {}", vote);
            this.voteRepository.save(vote);
            LOG.info("Polling DONE, vote with id {} saved.", vote.getId());
        }

        LOG.info("Polling DONE, poll enabled: {}.", voterPoll);
    }

}
