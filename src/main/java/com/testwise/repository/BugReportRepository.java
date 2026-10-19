package com.testwise.repository;
import com.testwise.model.BugReport;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BugReportRepository extends JpaRepository<BugReport, Long> {}
