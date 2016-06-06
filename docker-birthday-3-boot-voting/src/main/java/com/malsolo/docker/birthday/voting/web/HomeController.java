package com.malsolo.docker.birthday.voting.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.malsolo.docker.birthday.voting.domain.Options;
import com.malsolo.docker.birthday.voting.domain.VoteData;
import com.malsolo.docker.birthday.voting.repository.VoteDataRepository;

@Controller
@RequestMapping("/")
public class HomeController {

    private final static String COOKIE_VOTER_ID_NAME = "voter_id";

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    private final Options options;

    private final VoteDataRepository repository;

    @Autowired
    public HomeController(Options options, VoteDataRepository voteDataRepository) {
        this.options = options;
        this.repository = voteDataRepository;
    }

    @RequestMapping(method = GET)
    public String home(HttpServletRequest request, Model model) {
        fillModel(model, request.getRemoteHost(), null);
        return "index";
    }

    @RequestMapping(method = POST)
    public String processVote(@CookieValue(name = COOKIE_VOTER_ID_NAME, required = false) String voterId, @RequestParam String vote,
            HttpServletRequest request, HttpServletResponse response, Model model) {

        if (voterId == null) {
            // TODO
            // http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-random-values
            voterId = Long.toHexString(new Random().nextLong());
        }

        VoteData voteData = new VoteData(voterId, vote);
        LOG.debug("About to register vote data: {}", voteData);
        repository.rpush(voteData);

        fillModel(model, request.getRemoteHost(), vote);

        response.addCookie(new Cookie(COOKIE_VOTER_ID_NAME, voterId));

        return "index";
    }

    private void fillModel(Model model, String hostname, String vote) {
        model.addAttribute("option_a", options.getOptionA());
        model.addAttribute("option_b", options.getOptionB());
        model.addAttribute("hostname", hostname);
        model.addAttribute("vote", vote);
    }

}
