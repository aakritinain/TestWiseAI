package com.testwise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TestCase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, length=160) private String title;
    @Column(nullable=false, length=2000) private String steps;
    @Column(length=2000) private String expectedResult;
    @Enumerated(EnumType.STRING) private TestStatus status = TestStatus.NOT_RUN;
    private String priority = "MEDIUM";
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum TestStatus { NOT_RUN, PASS, FAIL, BLOCKED }
    public TestCase() {}
    public TestCase(String title, String steps, String expectedResult, String priority) {
        this.title=title; this.steps=steps; this.expectedResult=expectedResult; this.priority=priority;
    }
    public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getSteps(){return steps;} public void setSteps(String v){steps=v;}
    public String getExpectedResult(){return expectedResult;} public void setExpectedResult(String v){expectedResult=v;}
    public TestStatus getStatus(){return status;} public void setStatus(TestStatus v){status=v;}
    public String getPriority(){return priority;} public void setPriority(String v){priority=v;}
    public LocalDateTime getCreatedAt(){return createdAt;}
}
