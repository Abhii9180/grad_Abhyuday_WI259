package service;

import db.DBConnection;
import model.Admin;
import model.SiteOwner;
import model.User;

import java.sql.*;

public class AuthService {

    public static User login(String username, String password, String role) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=? AND role=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("user_id");

                if ("ADMIN".equals(role)) {
                    return new Admin(id, username);
                } else {
                    return new SiteOwner(id, username);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
