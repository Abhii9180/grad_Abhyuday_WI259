import model.User;
import service.AdminService;
import service.AuthService;
import service.OwnerService;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Select Role:");
        System.out.println("1. Admin");
        System.out.println("2. Owner");
        System.out.print("Enter choice: ");

        int roleChoice = sc.nextInt();
        sc.nextLine(); 

        String role;
        if (roleChoice == 1)
            role = "ADMIN";
        else if (roleChoice == 2)
            role = "OWNER";
        else {
            System.out.println("Invalid choice");
            return;
        }

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = AuthService.login(username, password, role);

        if (user == null) {
            System.out.println("Invalid credentials");
            return;
        }

        System.out.println("Logged in as: " + role);

        if ("ADMIN".equals(role)) {
            adminMenu(sc);
        } else {
            ownerMenu(sc, user.getUserId());
        }
    }

    
    private static void adminMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View all sites");
            System.out.println("2. Add new site");
            System.out.println("3. Assign site to owner");
            System.out.println("4. Calculate maintenance for site");
            System.out.println("5. View pending maintenance");
            System.out.println("6. Collect maintenance");

            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    AdminService.viewAllSites();
                    break;

                case 2:
                    AdminService.addSite(sc);
                    break;

                case 3:
                    AdminService.assignSiteToOwner(sc);
                    break;
                case 4:
                    System.out.print("Enter site ID: ");
                    int siteId = sc.nextInt();
                    AdminService.calculateMaintenanceForSite(siteId);
                    break;

                case 5:
                    AdminService.viewPendingMaintenance();
                    break;

                case 6:
                    System.out.print("Enter maintenance ID: ");
                    int mid = sc.nextInt();
                    AdminService.collectMaintenance(mid);
                    break;

                case 0:
                    System.out.println("Admin logged out");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    
    private static void ownerMenu(Scanner sc, int ownerId) {
        while (true) {
            System.out.println("\n--- OWNER MENU ---");
            System.out.println("1. View my sites");
            System.out.println("2. View my maintenance");
            System.out.println("3. Request update");
            System.out.println("4. View update request status");
            System.out.println("5. View update requests");
            System.out.println("6.Approve update requests");
            System.out.println("7. Reject update requests");

            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    OwnerService.viewMySites(ownerId);
                    break;
                case 2:
                    OwnerService.viewMyMaintenance(ownerId);
                    break;

                case 3:
                    OwnerService.requestUpdate(sc, ownerId);
                    break;

                case 4:
                    OwnerService.viewMyRequests(ownerId);
                    break;

                case 5:
                    AdminService.viewPendingRequests();
                    break;

                case 6:
                    System.out.print("Enter request ID: ");
                    AdminService.approveRequest(sc.nextInt());
                    break;

                case 7:
                    System.out.print("Enter request ID: ");
                    AdminService.rejectRequest(sc.nextInt());
                    break;

                case 0:
                    System.out.println("Owner logged out");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
