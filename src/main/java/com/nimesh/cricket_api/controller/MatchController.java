package com.nimesh.cricket_api.controller;

import javax.validation.Valid;

import com.nimesh.cricket_api.exception.ResourceNotFoundException;
import com.nimesh.cricket_api.model.Match;
import com.nimesh.cricket_api.repository.MatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricket/api/")
public class MatchController {
    @Autowired
    private MatchRepository matchRepository;
    
    // Get all matches
    @GetMapping("/matches")
    public Iterable<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    // Create a new match
    @PostMapping(path = "/matches")
    public Match addNewTeam(@Valid @RequestBody Match match) {
        return matchRepository.save(match);
    }

    // Get a match
    @GetMapping("/matches/{id}")
    public Match getMatchById(@PathVariable(value = "id") int matchId) {
        return matchRepository.findById(matchId).orElseThrow(() -> new ResourceNotFoundException("Match", "id", matchId));
    }

}
