package com.nimesh.cricket_api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "team1")
    private Integer team1;

    @Column(name = "team2")
    private Integer team2;

    private Integer winner;

    @Column(name = "inn1score")
    private Integer inn1_score;

    @Column(name = "inn2score")
    private Integer inn2_score;

    @Column(name = "inn1wickets")
    private Integer inn1_wickets;

    @Column(name = "inn2wickets")
    private Integer inn2_wickets;

    public Match() {
    }

    public Match(Integer team1, Integer team2, Integer winner, Integer inn1_score, Integer inn2_score,
            Integer inn1_wickets, Integer inn2_wickets) {
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.inn1_score = inn1_score;
        this.inn2_score = inn2_score;
        this.inn1_wickets = inn1_wickets;
        this.inn2_wickets = inn2_wickets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeam1() {
        return team1;
    }

    public void setTeam1(Integer team1) {
        this.team1 = team1;
    }

    public Integer getTeam2() {
        return team2;
    }

    public void setTeam2(Integer team2) {
        this.team2 = team2;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Integer getInn1_score() {
        return inn1_score;
    }

    public void setInn1_score(Integer inn1_score) {
        this.inn1_score = inn1_score;
    }

    public Integer getInn2_score() {
        return inn2_score;
    }

    public void setInn2_score(Integer inn2_score) {
        this.inn2_score = inn2_score;
    }

    public Integer getInn1_wickets() {
        return inn1_wickets;
    }

    public void setInn1_wickets(Integer inn1_wickets) {
        this.inn1_wickets = inn1_wickets;
    }

    public Integer getInn2_wickets() {
        return inn2_wickets;
    }

    public void setInn2_wickets(Integer inn2_wickets) {
        this.inn2_wickets = inn2_wickets;
    }

}
