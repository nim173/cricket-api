package com.nimesh.cricket_api.controller;

import javax.validation.Valid;

import com.nimesh.cricket_api.exception.ResourceNotFoundException;
import com.nimesh.cricket_api.model.Team;
import com.nimesh.cricket_api.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricket/api/")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    // Get all teams
    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Create a new team
    @PostMapping(path = "/teams")
    // CONTENT TYPE??????
    public Team addNewTeam(@Valid @RequestBody Team team) {
        return teamRepository.save(team);
    }

    // Get a team
    @GetMapping("/teams/{id}")
    public Team getTeamById(@PathVariable(value = "id") int teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("Team", "id", teamId));
    }

}
