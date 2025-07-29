package com.opensourcebim.bcfserver.models.enums;

public enum UserType {
    ADMIN (1),
    USER (2),
    SYSTEM (3),
    READONLY (4),
    MONITOR (5);

    private final int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserType fromValue(int value) {
        for (UserType type : UserType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid UserType value: " + value);
    }
}
