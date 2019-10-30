package hu.testathon.model.domain;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Validator {

    private static final int[] POINTS = {3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 6};

    private final String answer;

    public Validator(String answer) {
        this.answer = answer;
    }

    public String checkResult(String competitorAnswer) {
        return IntStream.range(0, answer.length())
                .mapToObj(i -> checkTask(competitorAnswer,i))
                .collect(Collectors.joining());
    }

    public int calcScore(String competitorAnswer) {
        return IntStream.range(0, answer.length())
                .map(i -> getScore(competitorAnswer, i))
                .sum();
    }

    public boolean isCorrect(String competitorAnswer, int taskNumber) {
        return answer.charAt(taskNumber) == competitorAnswer.charAt(taskNumber);
    }

    public String getAnswer() {
        return answer;
    }

    private String checkTask(String competitorAnswer, int i) {
        return isCorrect(competitorAnswer, i) ? "+" : " ";
    }

    private int getScore(String competitorAnswer, int i) {
        return isCorrect(competitorAnswer, i) ? POINTS[i] : 0;
    }
}
