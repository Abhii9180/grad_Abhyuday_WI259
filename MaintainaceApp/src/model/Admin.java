package model;

public class Admin extends User {
    public Admin(int userId, String username) {
        this.userId = userId;
        this.username = username;
        this.role = "ADMIN";
    }
}
