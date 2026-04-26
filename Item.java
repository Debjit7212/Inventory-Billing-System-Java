public class Item {
    int id;
    String name;
    int quantity;
    double price;

    public Item(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Converts the item data into a single line for the text file
    @Override
    public String toString() {
        return id + "," + name + "," + quantity + "," + price;
    }
}