package test.david.notificationTest.enums;

import lombok.Getter;

public enum CategoryEnum {

    SPORTS("Sports"),
    FINANCE("Finance"),
    MOVIES("Movies");

    private final String description;

    CategoryEnum(final String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
