package com.testwise.controller;

import com.testwise.model.BugReport;
import com.testwise.model.TestCase;
import com.testwise.repository.BugReportRepository;
import com.testwise.repository.TestCaseRepository;
import com.testwise.service.AiAssistantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {
    private final TestCaseRepository tests;
    private final BugReportRepository bugs;
    private final AiAssistantService ai;
    public ApiController(TestCaseRepository tests, BugReportRepository bugs, AiAssistantService ai) {
        this.tests=tests; this.bugs=bugs; this.ai=ai;
    }

    public record TestInput(@NotBlank String title, @NotBlank String steps, String expectedResult, String priority) {}
    public record BugInput(@NotBlank String title, @NotBlank String description, String stepsToReproduce,
                           String expectedResult, String actualResult, String severity) {}
    public record RequirementInput(@NotBlank String requirement) {}
    public record BugAnalysisInput(@NotBlank String title, @NotBlank String description, String expectedResult, String actualResult) {}
    public record StatusInput(@NotBlank String status) {}

    @GetMapping("/tests") public List<TestCase> getTests(){ return tests.findAll(); }
    @PostMapping("/tests") @ResponseStatus(HttpStatus.CREATED)
    public TestCase createTest(@Valid @RequestBody TestInput in) {
        return tests.save(new TestCase(in.title(), in.steps(), in.expectedResult(), safe(in.priority(),"MEDIUM")));
    }
    @PatchMapping("/tests/{id}/status")
    public TestCase updateTestStatus(@PathVariable Long id, @Valid @RequestBody StatusInput in) {
        TestCase t=tests.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        try { t.setStatus(TestCase.TestStatus.valueOf(in.status().toUpperCase(Locale.ROOT))); }
        catch (IllegalArgumentException ex) { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status must be NOT_RUN, PASS, FAIL, or BLOCKED"); }
        return tests.save(t);
    }
    @DeleteMapping("/tests/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTest(@PathVariable Long id){ tests.deleteById(id); }

    @GetMapping("/bugs") public List<BugReport> getBugs(){ return bugs.findAll(); }
    @PostMapping("/bugs") @ResponseStatus(HttpStatus.CREATED)
    public BugReport createBug(@Valid @RequestBody BugInput in) {
        return bugs.save(new BugReport(in.title(),in.description(),in.stepsToReproduce(),in.expectedResult(),in.actualResult(),safe(in.severity(),"MEDIUM")));
    }
    @PatchMapping("/bugs/{id}/status")
    public BugReport updateBugStatus(@PathVariable Long id, @Valid @RequestBody StatusInput in) {
        BugReport b=bugs.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Set<String> allowed=Set.of("OPEN","IN_PROGRESS","RESOLVED","CLOSED");
        String status=in.status().toUpperCase(Locale.ROOT);
        if(!allowed.contains(status)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Status must be OPEN, IN_PROGRESS, RESOLVED, or CLOSED");
        b.setStatus(status); return bugs.save(b);
    }
    @DeleteMapping("/bugs/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBug(@PathVariable Long id){ bugs.deleteById(id); }

    @PostMapping("/ai/test-cases")
    public Map<String,String> generate(@Valid @RequestBody RequirementInput in) {
        return Map.of("suggestions", ai.generateTestCases(in.requirement()));
    }
    @PostMapping("/ai/analyze-bug")
    public Map<String,String> analyze(@Valid @RequestBody BugAnalysisInput in) {
        return Map.of("analysis", ai.analyzeBug(in.title(),in.description(),in.expectedResult(),in.actualResult()));
    }
    @GetMapping("/dashboard")
    public Map<String,Object> dashboard() {
        List<TestCase> allTests=tests.findAll(); List<BugReport> allBugs=bugs.findAll();
        long passed=allTests.stream().filter(t->t.getStatus()==TestCase.TestStatus.PASS).count();
        long failed=allTests.stream().filter(t->t.getStatus()==TestCase.TestStatus.FAIL).count();
        long open=allBugs.stream().filter(b->"OPEN".equals(b.getStatus())||"IN_PROGRESS".equals(b.getStatus())).count();
        return Map.of("totalTests",allTests.size(),"passedTests",passed,"failedTests",failed,
          "notRunTests",allTests.stream().filter(t->t.getStatus()==TestCase.TestStatus.NOT_RUN).count(),
          "totalBugs",allBugs.size(),"openBugs",open);
    }
    private String safe(String value,String fallback){ return value==null||value.isBlank()?fallback:value.trim().toUpperCase(Locale.ROOT); }
}
