package ru.truehack.backend.assistant.model;

public class GestureRequest {
    private String gesture;
    private String uiTreeJson;
    private String lang;

    public String getGesture() {
        return gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }

    public String getUiTreeJson() {
        return uiTreeJson;
    }

    public void setUiTreeJson(String uiTreeJson) {
        this.uiTreeJson = uiTreeJson;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
