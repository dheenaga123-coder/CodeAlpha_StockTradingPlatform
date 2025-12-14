public class Transaction {
    private String type;
    private String stock;
    private int quantity;
    private double price;

    public Transaction(String type, String stock, int quantity, double price) {
        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return type + " | " + stock + 
               " | Qty: " + quantity + 
               " | Price: ₹" + price;
    }
}
