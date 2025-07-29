package com.opensourcebim.bcfserver.models.enums;

public enum Schema {
    IFC4 (1),
    IFC_2X3 (2);

    private final int value;

    Schema(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Schema fromValue(int value) {
        for (Schema type : Schema.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Schema value: " + value);
    }
}
