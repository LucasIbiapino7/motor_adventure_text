package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Dialogue {
    private String text;
    private List<Result> responses = new ArrayList<>();

    public Dialogue() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Result> getResponses() {
        return responses;
    }

    public void setResponses(List<Result> responses) {
        this.responses = responses;
    }

    public void addResult(Result result){
        responses.add(result);
    }

    @Override
    public String toString() {
        return "Dialogue{" +
                "text='" + text + '\'' +
                ", responses=" + responses +
                '}';
    }
}
