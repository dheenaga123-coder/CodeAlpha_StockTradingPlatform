import java.util.*;

public class Market {

    private List<Stock> stocks = new ArrayList<>();

    public Market() {
        stocks.add(new Stock("TCS", 3500));
        stocks.add(new Stock("INFY", 1500));
        stocks.add(new Stock("RELIANCE", 2500));
    }

    public Stock getStock(String symbol) {
        for (Stock s : stocks) {
            if (s.getSymbol().equalsIgnoreCase(symbol)) {
                return s;
            }
        }
        return null;
    }

    public List<Stock> getAllStocks() {
        return stocks;
    }
}
