package org.example.entities;

public class Enemy {
    private Integer attack;
    private Integer defense;
    private Result result;

    public Enemy() {
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "attack=" + attack +
                ", defense=" + defense +
                ", result=" + result +
                '}';
    }
}
