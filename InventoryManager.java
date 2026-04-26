import java.util.*;
import java.io.*;

public class InventoryManager {
    private List<Item> inventory = new ArrayList<>();
    private final String FILE_NAME = "inventory.txt";

    public InventoryManager() {
        loadFromFile();
    }

    public void addItem(String name, int qty, double price) {
        int id = inventory.size() + 1;
        inventory.add(new Item(id, name, qty, price));
        saveToFile();
        System.out.println("✅ Item added successfully!");
    }

    public void viewInventory() {
        System.out.println("\n--- Current Inventory ---");
        System.out.printf("%-5s %-15s %-10s %-10s\n", "ID", "Name", "Qty", "Price");
        for (Item item : inventory) {
            System.out.printf("%-5d %-15s %-10d %-10.2f\n", item.id, item.name, item.quantity, item.price);
        }
    }

    public void generateBill(int itemId, int buyQty) {
        for (Item item : inventory) {
            if (item.id == itemId) {
                if (item.quantity >= buyQty) {
                    double total = item.price * buyQty;
                    double tax = total * 0.05; // 5% GST
                    double finalBill = total + tax;

                    item.quantity -= buyQty; // Stock reduction
                    saveToFile();

                    System.out.println("\n======= INVOICE =======");
                    System.out.println("Item:         " + item.name);
                    System.out.println("Quantity:     " + buyQty);
                    System.out.println("Subtotal:     " + total);
                    System.out.println("Tax (5%):     " + tax);
                    System.out.println("Total Amount: " + finalBill);
                    System.out.println("=======================");
                    return;
                } else {
                    System.out.println("❌ Error: Insufficient stock!");
                    return;
                }
            }
        }
        System.out.println("❌ Error: Item ID not found.");
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Item item : inventory) {
                writer.println(item.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            inventory.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                inventory.add(new Item(
                    Integer.parseInt(parts[0]), 
                    parts[1], 
                    Integer.parseInt(parts[2]), 
                    Double.parseDouble(parts[3])
                ));
            }
        } catch (IOException e) {
            System.out.println("Error loading data.");
        }
    }
}