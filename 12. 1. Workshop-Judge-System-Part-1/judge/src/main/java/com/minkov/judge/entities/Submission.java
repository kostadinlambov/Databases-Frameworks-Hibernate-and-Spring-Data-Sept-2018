package com.minkov.judge.entities;

import com.minkov.judge.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "submissions")
public class Submission extends BaseEntity {
    private User user;
    private Strategy strategy;
    private Double performance;
    private Double points;
    private Problem problem;

    @ManyToOne
    public User getUser() {
        return user;
    }

    @ManyToOne
    public Strategy getStrategy() {
        return strategy;
    }

    @Column
    public Double getPerformance() {
        return performance;
    }

    @Column
    public Double getPoints() {
        return points;
    }

    @ManyToOne
    public Problem getProblem() {
        return problem;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
