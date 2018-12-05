package com.minkov.judge.entities;

import com.minkov.judge.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
public class User extends BaseEntity {
    private String username;
    private Set<Submission> submissions;
    private Set<MaxResultForProblem> maxResultForProblems;
    private Set<MaxResultForContest> maxResultsForContests;
    private Set<Contest> contests;
    private Set<Problem> problems;

    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    @OneToMany(mappedBy = "user")
    public Set<Submission> getSubmissions() {
        return submissions;
    }

    @OneToMany(mappedBy = "user")
    public Set<MaxResultForProblem> getMaxResultForProblems() {
        return maxResultForProblems;
    }

    @OneToMany(mappedBy = "user")
    public Set<MaxResultForContest> getMaxResultsForContests() {
        return maxResultsForContests;
    }

    @ManyToMany
    @JoinTable(
            name = "users_contests",
            joinColumns =
                    @JoinColumn(
                            name = "user_id",
                            referencedColumnName = "id"
                    ),
            inverseJoinColumns =
                    @JoinColumn(
                            name = "contest_id",
                            referencedColumnName = "id"
                    )
    )
    public Set<Contest> getContests() {
        return contests;
    }

    @ManyToMany()
    @JoinTable(
            name = "users_problems",
            joinColumns =
            @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns =
            @JoinColumn(
                    name = "problem_id",
                    referencedColumnName = "id"
            )
    )
    public Set<Problem> getProblems() {
        return problems;
    }

    public void setMaxResultForProblems(Set<MaxResultForProblem> maxResultForProblems) {
        this.maxResultForProblems = maxResultForProblems;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    public void setMaxResultsForContests(Set<MaxResultForContest> maxResultsForContests) {
        this.maxResultsForContests = maxResultsForContests;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }


    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }
}
