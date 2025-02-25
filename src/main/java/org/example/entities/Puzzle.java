package org.example.entities;

public class Puzzle {
    private Integer id;
    private String description;
    private Solution solution;
    private Result result;

    public Puzzle() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", solution=" + solution +
                ", result=" + result +
                '}';
    }
}
