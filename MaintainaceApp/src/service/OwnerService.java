package service;

import db.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class OwnerService {

    public static void viewMySites(int ownerId) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM sites WHERE owner_id=?");
            ps.setInt(1, ownerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Site ID: " + rs.getInt("site_id") +
                        " | Type: " + rs.getString("site_type")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void viewMyMaintenance(int ownerId) {
    try {
        Connection con = DBConnection.getConnection();

        String sql =
            "SELECT s.site_id, m.amount, m.status " +
            "FROM sites s JOIN maintenance m ON s.site_id = m.site_id " +
            "WHERE s.owner_id=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ownerId);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- MY MAINTENANCE ---");
        while (rs.next()) {
            System.out.println(
                "Site ID: " + rs.getInt("site_id") +
                ", Amount: Rs " + rs.getDouble("amount") +
                ", Status: " + rs.getString("status")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public static void requestUpdate(Scanner sc, int ownerId) {
    try {
        System.out.println("Request Update:");
        System.out.println("1. Update phone");
        System.out.println("2. Update email");
        System.out.println("3. Update site type");
        System.out.print("Choose option: ");

        int choice = sc.nextInt();
        sc.nextLine();

        String field = null;
        String oldValue = null;
        String newValue = null;
        int siteId = 0;

        Connection con = DBConnection.getConnection();

        if (choice == 1) {
            field = "contact_number";
            System.out.print("Enter new phone: ");
            newValue = sc.nextLine();

            String q = "SELECT contact_number FROM site_owners WHERE owner_id=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, ownerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) oldValue = rs.getString(1);

        } else if (choice == 2) {
            field = "email";
            System.out.print("Enter new email: ");
            newValue = sc.nextLine();

            String q = "SELECT email FROM site_owners WHERE owner_id=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, ownerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) oldValue = rs.getString(1);

        } else if (choice == 3) {
            field = "site_type";
            System.out.print("Enter site ID: ");
            siteId = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new site type (OPEN/OCCUPIED): ");
            newValue = sc.nextLine();

            String q = "SELECT site_type FROM sites WHERE site_id=? AND owner_id=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, siteId);
            ps.setInt(2, ownerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) oldValue = rs.getString(1);
            else {
                System.out.println("Invalid site or not your site");
                return;
            }
        } else {
            System.out.println("Invalid choice");
            return;
        }

        String insert =
            "INSERT INTO update_requests (owner_id, site_id, requested_field, old_value, new_value) " +
            "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps2 = con.prepareStatement(insert);
        ps2.setInt(1, ownerId);
        ps2.setInt(2, siteId);
        ps2.setString(3, field);
        ps2.setString(4, oldValue);
        ps2.setString(5, newValue);
        ps2.executeUpdate();

        System.out.println("Update request submitted (PENDING)");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void viewMyRequests(int ownerId) {
    try {
        Connection con = DBConnection.getConnection();
        String sql =
            "SELECT request_id, requested_field, old_value, new_value, status " +
            "FROM update_requests WHERE owner_id=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ownerId);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- MY UPDATE REQUESTS ---");
        while (rs.next()) {
            System.out.println(
                "Request ID: " + rs.getInt("request_id") +
                ", Field: " + rs.getString("requested_field") +
                ", Status: " + rs.getString("status")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}





}
