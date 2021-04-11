package ru.uoles.proj.model;

public class Property {

    private String fromUsername;
    private String fromPassword;
    private String toUsername;
    private String toPassword;

    public Property() {
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public String getFromPassword() {
        return fromPassword;
    }

    public String getToUsername() {
        return toUsername;
    }

    public String getToPassword() {
        return toPassword;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public void setFromPassword(String fromPassword) {
        this.fromPassword = fromPassword;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public void setToPassword(String toPassword) {
        this.toPassword = toPassword;
    }
}
