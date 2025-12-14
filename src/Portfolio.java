import java.util.*;

public class Portfolio {

    private Map<String, Integer> holdings = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void buy(String stock, int qty, double price) {
        holdings.put(stock, holdings.getOrDefault(stock, 0) + qty);
        transactions.add(new Transaction("BUY", stock, qty, price));
    }

    public void sell(String stock, int qty, double price) {
        int currentQty = holdings.getOrDefault(stock, 0);

        if (currentQty >= qty) {
            holdings.put(stock, currentQty - qty);
            transactions.add(new Transaction("SELL", stock, qty, price));
        } else {
            throw new RuntimeException("Not enough stocks to sell");
        }
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
