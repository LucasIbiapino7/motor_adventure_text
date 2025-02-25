package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Npc {
    private Integer id;
    private String name;
    private String description;
    private Boolean inactive;
    private List<Dialogue> dialogues = new ArrayList<>();

    public Npc() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }

    public void setDialogues(List<Dialogue> dialogues) {
        this.dialogues = dialogues;
    }

    @Override
    public String toString() {
        return "Npc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", inactive=" + inactive +
                ", dialogues=" + dialogues +
                '}';
    }

    public void addDialogue(Dialogue dialogue) {
        dialogues.add(dialogue);
    }
}
