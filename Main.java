import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        while (true) {
            System.out.println("\n--- Billing System Menu ---");
            System.out.println("1. Add New Item (Admin)");
            System.out.println("2. View Inventory");
            System.out.println("3. Generate Bill");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Item Name: ");
                    String name = sc.next();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    manager.addItem(name, qty, price);
                    break;
                case 2:
                    manager.viewInventory();
                    break;
                case 3:
                    System.out.print("Enter Item ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Buy Quantity: ");
                    int bQty = sc.nextInt();
                    manager.generateBill(id, bQty);
                    break;
                case 4:
                    System.out.println("Thank you for using the system. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}