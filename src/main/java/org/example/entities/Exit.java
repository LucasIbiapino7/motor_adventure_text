package org.example.entities;

public class Exit {
    private Integer targetLocationId;
    private String direction;
    private String description;
    private Boolean inactive;

    public Exit() {
    }

    public Integer getTargetLocationId() {
        return targetLocationId;
    }

    public void setTargetLocationId(Integer targetLocationId) {
        this.targetLocationId = targetLocationId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    @Override
    public String toString() {
        return "Exit{" +
                "targetLocationId=" + targetLocationId +
                ", direction='" + direction + '\'' +
                ", description='" + description + '\'' +
                ", inactive=" + inactive +
                '}';
    }
}
