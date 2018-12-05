package com.minkov.judge.entities;

import com.minkov.judge.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "tests")
public class Test extends BaseEntity {
    private String expectedResult;
    private String testContent;
    private Problem problem;

    @Column(name = "expected_result")
    public String getExpectedResult() {
        return expectedResult;
    }

    @Column(name = "test_content")
    public String getTestContent() {
        return testContent;
    }

    @ManyToOne
    public Problem getProblem() {
        return problem;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
