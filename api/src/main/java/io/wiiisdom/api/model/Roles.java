package io.wiiisdom.api.model;

public enum Roles {
    ADMIN("administrator"),
    COLLAB("collaborator");

    private String role;

    private Roles(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}
