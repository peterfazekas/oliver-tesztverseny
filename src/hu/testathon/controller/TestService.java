package hu.testathon.controller;

import hu.testathon.model.domain.FinalResult;
import hu.testathon.model.domain.TestResult;
import hu.testathon.model.domain.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestService {

    private final Validator validator;
    private final List<TestResult> testResults;

    public TestService(Validator validator, List<TestResult> testResults) {
        this.validator = validator;
        this.testResults = testResults;
    }

    public int getCompetitorNumber() {
        return testResults.size();
    }

    public String getAnswerById(String id) {
        return getTestResultById(id).getAnswer();
    }

    public String getCheckedResult(String id) {
        return String.format("%s\t(A helyes megoldás)%n%s\t(A versenyző helyes válaszai)",
                validator.getAnswer(), validator.checkResult(getTestResultById(id).getAnswer()));
    }

    public String getCorrectAnswerStatistic(int taskNumber) {
        long countCorrectAnswers = countCorrectAnswers(taskNumber - 1);
        double percent = countCorrectAnswers * 100.0 / getCompetitorNumber() ;
        return String.format("A feladatra %d fő, a versenyzők %5.2f%%-a adott helyes választ.", countCorrectAnswers, percent);
    }

    public List<String> getFinalScores() {
        return calculateScores().stream()
                .map(i -> i.getId() + " " + i.getScore())
                .collect(Collectors.toList());
    }

    public String getOrderedResults() {
        return createOrderedResults().stream()
                .filter(i -> i.getOrder() <= 3)
                .map(FinalResult::toString)
                .collect(Collectors.joining("\n"));
    }

    private TestResult getTestResultById(String id) {
        return testResults.stream()
                .filter(i -> i.getId().equals(id))
                .findAny()
                .get();
    }

    private long countCorrectAnswers(int taskNumber) {
        return testResults.stream()
                .map(TestResult::getAnswer)
                .filter(answer -> validator.isCorrect(answer, taskNumber))
                .count();
    }

    private List<FinalResult> calculateScores() {
        return testResults.stream()
                .map(this::createFinalResult)
                .collect(Collectors.toList());
    }

    private FinalResult createFinalResult(TestResult testResult) {
        return new FinalResult(testResult.getId(), validator.calcScore(testResult.getAnswer()));
    }

    private List<FinalResult> createOrderedResults() {
        List<FinalResult> sortedResults = createSortedResults();
        FinalResult prevResult = new FinalResult(0, "", 0);
        List<FinalResult> orderedResults = new ArrayList<>();
        for (FinalResult finalResult : sortedResults) {
            int order = finalResult.getScore() == prevResult.getScore() ? prevResult.getOrder() : prevResult.getOrder() + 1;
            FinalResult newOrderedResult = createFinalResult(order, finalResult);
            orderedResults.add(newOrderedResult);
            prevResult = newOrderedResult;
        }
        return orderedResults;
    }

    private List<FinalResult> createSortedResults() {
        return calculateScores().stream()
                .sorted((i, j) -> j.getScore().compareTo(i.getScore()))
                .collect(Collectors.toList());
    }

    private FinalResult createFinalResult(int order, FinalResult finalResult) {
        return new FinalResult(order, finalResult.getId(), finalResult.getScore());
    }

}
