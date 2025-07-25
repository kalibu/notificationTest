package test.david.notificationTest.enums;

import lombok.Getter;

public enum CategoryEnum {

    SPORTS("Sports"),
    FINANCE("Finance"),
    MOVIES("Movies");

    @Getter
    private final String description;

    CategoryEnum(final String description) {
        this.description = description;
    }
}
