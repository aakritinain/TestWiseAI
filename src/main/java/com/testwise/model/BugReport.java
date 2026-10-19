package com.testwise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BugReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, length=160) private String title;
    @Column(nullable=false, length=3000) private String description;
    @Column(length=2000) private String stepsToReproduce;
    @Column(length=2000) private String expectedResult;
    @Column(length=2000) private String actualResult;
    private String severity = "MEDIUM";
    private String status = "OPEN";
    @Column(length=3000) private String aiAnalysis;
    private LocalDateTime createdAt = LocalDateTime.now();

    public BugReport() {}
    public BugReport(String title, String description, String stepsToReproduce, String expectedResult, String actualResult, String severity) {
        this.title=title; this.description=description; this.stepsToReproduce=stepsToReproduce;
        this.expectedResult=expectedResult; this.actualResult=actualResult; this.severity=severity;
    }
    public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getStepsToReproduce(){return stepsToReproduce;} public void setStepsToReproduce(String v){stepsToReproduce=v;}
    public String getExpectedResult(){return expectedResult;} public void setExpectedResult(String v){expectedResult=v;}
    public String getActualResult(){return actualResult;} public void setActualResult(String v){actualResult=v;}
    public String getSeverity(){return severity;} public void setSeverity(String v){severity=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getAiAnalysis(){return aiAnalysis;} public void setAiAnalysis(String v){aiAnalysis=v;}
    public LocalDateTime getCreatedAt(){return createdAt;}
}
