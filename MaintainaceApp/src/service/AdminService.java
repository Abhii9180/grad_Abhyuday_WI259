package service;

import db.DBConnection;
import model.Site;

import java.sql.*;
import java.util.Scanner;

public class AdminService {

    // 1. To View all sites
    public static void viewAllSites() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sites");

            System.out.println("\n--- ALL SITES ---"); 
            
            while (rs.next()) {
                System.out.println(
                        "Site ID: " + rs.getInt("site_id") +
                        ", Size: " + rs.getInt("length") + "x" + rs.getInt("width") +
                        ", Type: " + rs.getString("site_type") +
                        ", Owner ID: " + rs.getInt("owner_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2. Add a new site
    public static void addSite(Scanner sc) {
        try {
            System.out.print("Enter length: ");
            int length = sc.nextInt();

            System.out.print("Enter width: ");
            int width = sc.nextInt();

            System.out.print("Site type (OPEN / OCCUPIED): ");
            String siteType = sc.next();

            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO sites (length, width, site_type, maintenance_status) VALUES (?, ?, ?, 'PENDING')";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, length);
            ps.setInt(2, width);
            ps.setString(3, siteType.toUpperCase());

            ps.executeUpdate();
            System.out.println("Site added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. Assign site to owner
    public static void assignSiteToOwner(Scanner sc) {
        try {
            System.out.print("Enter site ID: ");
            int siteId = sc.nextInt();

            System.out.print("Enter owner ID: ");
            int ownerId = sc.nextInt();

            Connection con = DBConnection.getConnection();
            String sql = "UPDATE sites SET owner_id=? WHERE site_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ownerId);
            ps.setInt(2, siteId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Site assigned to owner successfully");
            } else {
                System.out.println("Invalid site ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public static void calculateMaintenanceForSite(int siteId) {
    try {
        Connection con = DBConnection.getConnection();

        // get site details
        String siteSql = "SELECT length, width, site_type FROM sites WHERE site_id=?";
        PreparedStatement ps1 = con.prepareStatement(siteSql);
        ps1.setInt(1, siteId);
        ResultSet rs = ps1.executeQuery();

        if (!rs.next()) {
            System.out.println("Invalid site ID");
            return;
        }

        int length = rs.getInt("length");
        int width = rs.getInt("width");
        String siteType = rs.getString("site_type");

        Site site = new Site(length, width, siteType);
        double amount = site.calculateMaintenance();

        // insert into maintenance table
        String insertSql =
            "INSERT INTO maintenance (site_id, amount, due_date, status) " +
            "VALUES (?, ?, CURRENT_DATE + INTERVAL '30 days', 'PENDING')";

        PreparedStatement ps2 = con.prepareStatement(insertSql);
        ps2.setInt(1, siteId);
        ps2.setDouble(2, amount);
        ps2.executeUpdate();

        System.out.println("Maintenance calculated: Rs " + amount);

    } catch (Exception e) {
        e.printStackTrace();
    }
}



public static void viewPendingMaintenance() {
    try {
        Connection con = DBConnection.getConnection();
        String sql =
            "SELECT m.maintenance_id, m.site_id, m.amount " +
            "FROM maintenance m WHERE m.status='PENDING'";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("\n--- PENDING MAINTENANCE ---");
        while (rs.next()) {
            System.out.println(
                "Maintenance ID: " + rs.getInt("maintenance_id") +
                ", Site ID: " + rs.getInt("site_id") +
                ", Amount: Rs " + rs.getDouble("amount")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}



public static void collectMaintenance(int maintenanceId) {
    try {
        Connection con = DBConnection.getConnection();
        String sql =
            "UPDATE maintenance SET status='PAID', payment_date=CURRENT_DATE " +
            "WHERE maintenance_id=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, maintenanceId);

        int rows = ps.executeUpdate();
        if (rows > 0) {
            System.out.println("Maintenance marked as PAID");
        } else {
            System.out.println("Invalid maintenance ID");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}


public static void viewPendingRequests() {
    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM update_requests WHERE status='PENDING'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("\n--- PENDING UPDATE REQUESTS ---");
        while (rs.next()) {
            System.out.println(
                "Request ID: " + rs.getInt("request_id") +
                ", Owner ID: " + rs.getInt("owner_id") +
                ", Field: " + rs.getString("requested_field") +
                ", New Value: " + rs.getString("new_value")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}



public static void approveRequest(int requestId) {
    try {
        Connection con = DBConnection.getConnection();

        String q = "SELECT * FROM update_requests WHERE request_id=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, requestId);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            System.out.println("Invalid request ID");
            return;
        }

        int ownerId = rs.getInt("owner_id");
        int siteId = rs.getInt("site_id");
        String field = rs.getString("requested_field");
        String newValue = rs.getString("new_value");

        if ("contact_number".equals(field) || "email".equals(field)) {
            String update =
                "UPDATE site_owners SET " + field + "=? WHERE owner_id=?";
            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setString(1, newValue);
            ps2.setInt(2, ownerId);
            ps2.executeUpdate();

        } else if ("site_type".equals(field)) {
            String update =
                "UPDATE sites SET site_type=? WHERE site_id=?";
            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setString(1, newValue);
            ps2.setInt(2, siteId);
            ps2.executeUpdate();
        }

        String statusUpdate =
            "UPDATE update_requests SET status='APPROVED' WHERE request_id=?";
        PreparedStatement ps3 = con.prepareStatement(statusUpdate);
        ps3.setInt(1, requestId);
        ps3.executeUpdate();

        System.out.println("Request approved and applied");

    } catch (Exception e) {
        e.printStackTrace();
    }
}




public static void rejectRequest(int requestId) {
    try {
        Connection con = DBConnection.getConnection();
        String sql =
            "UPDATE update_requests SET status='REJECTED' WHERE request_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, requestId);

        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Request rejected");
        else
            System.out.println("Invalid request ID");

    } catch (Exception e) {
        e.printStackTrace();
    }
}




}
