package hu.testathon.model.domain;

public class FinalResult {

    private final int order;
    private final String id;
    private final Integer score;

    public FinalResult(String id, Integer score) {
        this.order = 0;
        this.id = id;
        this.score = score;
    }

    public FinalResult(int order, String id, Integer score) {
        this.order = order;
        this.id = id;
        this.score = score;
    }

    public int getOrder() {
        return order;
    }

    public String getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String toString() {
        return order + ". díj (" + score + " pont): " + id;
    }
}
