package com.minkov.judge.entities;

import com.minkov.judge.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "max_results_for_problems")
public class MaxResultForProblem extends BaseEntity {
    private User user;
    private Problem problem;
    private Submission submission;

    @ManyToOne
    public User getUser() {
        return user;
    }

    @ManyToOne
    public Problem getProblem() {
        return problem;
    }

    @ManyToOne
    public Submission getSubmission() {
        return submission;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}
