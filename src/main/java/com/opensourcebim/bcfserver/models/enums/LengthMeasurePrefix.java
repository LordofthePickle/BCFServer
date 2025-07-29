package com.opensourcebim.bcfserver.models.enums;

public enum LengthMeasurePrefix {
    METER (0),
    ATTOMETER (-18),
    FEMTOMETER (-15),
    PICOMETER (-12),
    NANOMETER (-9),
    MICROMETER (-6),
    MILLIMETER (-3),
    CENTIMETER (-2),
    DECIMETER (-1),
    DECAMETER (1),
    HECTOMETER (2),
    KILOMETER (3),
    MEGAMETER (6),
    GIGAMETER (9),
    TERAMETER (12),
    PETAMETER (15),
    EXAMETER (18);

    private final int value;

    LengthMeasurePrefix(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LengthMeasurePrefix fromValue(int value) {
        for (LengthMeasurePrefix type : LengthMeasurePrefix.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid LengthMeasurePrefix value: " + value);
    }
}
