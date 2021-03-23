package com.nimesh.cricket_api.repository;

import java.util.List;

import com.nimesh.cricket_api.model.Match;

import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Integer> {
    public List<Match> findByTeam1OrTeam2(int teamId1, int teamId2);
}
