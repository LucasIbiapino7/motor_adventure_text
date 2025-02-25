package org.example.entities;

import jakarta.json.JsonArray;
import jakarta.json.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Integer> requiredItems = new ArrayList<>();
    private String actions;

    public Solution() {
    }

    public List<Integer> getRequiredItems() {
        return requiredItems;
    }

    public void setRequiredItems(JsonArray requiredItems) {
        for (JsonValue requiredItem : requiredItems) {
            this.requiredItems.add(Integer.valueOf(requiredItem.toString()));
        }
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "requiredItems=" + requiredItems +
                ", actions='" + actions + '\'' +
                '}';
    }
}
