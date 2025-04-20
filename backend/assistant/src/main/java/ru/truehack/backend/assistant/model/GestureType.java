package ru.truehack.backend.assistant.model;

public enum GestureType {
    CIRCLE;

    public static GestureType fromString(String gesture) {
        try {
            return GestureType.valueOf(gesture.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
