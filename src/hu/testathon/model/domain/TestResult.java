package hu.testathon.model.domain;

public class TestResult {

    private final String id;
    private final String answer;

    public TestResult(String id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }
}
