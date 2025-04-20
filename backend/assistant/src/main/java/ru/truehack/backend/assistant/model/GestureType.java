package ru.truehack.backend.assistant.model;

public enum GestureType {
    DOUBLE_TAP;

    public static GestureType fromString(String gesture) {
        try {
            return GestureType.valueOf(gesture.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
