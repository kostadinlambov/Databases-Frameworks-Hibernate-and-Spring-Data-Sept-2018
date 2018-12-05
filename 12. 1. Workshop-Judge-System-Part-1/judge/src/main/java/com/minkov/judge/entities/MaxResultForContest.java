package com.minkov.judge.entities;

import com.minkov.judge.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "max_results_for_contests")
public class MaxResultForContest extends BaseEntity {
    private User user;
    private Contest contest;
    private Double averagePerformance;
    private Double totalPoints;

    @ManyToOne
    public User getUser() {
        return user;
    }

    @ManyToOne
    public Contest getContest() {
        return contest;
    }

    @Column(name = "average_performance")
    public Double getAveragePerformance() {
        return averagePerformance;
    }

    @Column(name = "total_points")
    public Double getTotalPoints() {
        return totalPoints;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public void setAveragePerformance(Double averagePerformance) {
        this.averagePerformance = averagePerformance;
    }

    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }
}
