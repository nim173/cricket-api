package com.nimesh.cricket_api.repository;

import com.nimesh.cricket_api.model.Match;

import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Integer>{
    
}
