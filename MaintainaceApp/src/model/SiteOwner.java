package model;

public class SiteOwner extends User {
    public SiteOwner(int userId, String username) {
        this.userId = userId;
        this.username = username;
        this.role = "OWNER";
    }
}
