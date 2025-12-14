import javax.swing.*;
import java.awt.*;

public class TradingGUI extends JFrame {

    private Market market = new Market();
    private Portfolio portfolio = new Portfolio();

    private JTextField stockField, qtyField;
    private JTextArea outputArea;

    public TradingGUI() {

        setTitle("Stock Trading Platform");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- TOP PANEL ----------
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        topPanel.add(new JLabel("Stock Name (TCS / INFY / RELIANCE):"));
        stockField = new JTextField();
        topPanel.add(stockField);

        topPanel.add(new JLabel("Quantity:"));
        qtyField = new JTextField();
        topPanel.add(qtyField);

        JButton buyBtn = new JButton("BUY");
        JButton sellBtn = new JButton("SELL");

        topPanel.add(buyBtn);
        topPanel.add(sellBtn);

        add(topPanel, BorderLayout.NORTH);

        // ---------- CENTER OUTPUT ----------
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // ---------- BOTTOM PANEL ----------
        JPanel bottomPanel = new JPanel();

        JButton viewPortfolioBtn = new JButton("View Portfolio");
        JButton viewTransactionsBtn = new JButton("View Transactions");

        bottomPanel.add(viewPortfolioBtn);
        bottomPanel.add(viewTransactionsBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // ---------- BUTTON ACTIONS ----------

        buyBtn.addActionListener(e -> {
            try {
                String stock = stockField.getText();
                int qty = Integer.parseInt(qtyField.getText());

                Stock s = market.getStock(stock);
                if (s != null) {
                    portfolio.buy(stock, qty, s.getPrice());
                    outputArea.setText("✅ Bought " + qty + " shares of " + stock);
                } else {
                    outputArea.setText("❌ Stock not found");
                }
            } catch (Exception ex) {
                outputArea.setText("⚠ Invalid input");
            }
        });

        sellBtn.addActionListener(e -> {
            try {
                String stock = stockField.getText();
                int qty = Integer.parseInt(qtyField.getText());

                Stock s = market.getStock(stock);
                if (s != null) {
                    portfolio.sell(stock, qty, s.getPrice());
                    outputArea.setText("✅ Sold " + qty + " shares of " + stock);
                }
            } catch (Exception ex) {
                outputArea.setText("⚠ Error: " + ex.getMessage());
            }
        });

        viewPortfolioBtn.addActionListener(e -> {
            outputArea.setText("📊 PORTFOLIO\n");
            for (String key : portfolio.getHoldings().keySet()) {
                outputArea.append(key + " : " +
                        portfolio.getHoldings().get(key) + "\n");
            }
        });

        viewTransactionsBtn.addActionListener(e -> {
            outputArea.setText("📜 TRANSACTIONS\n");
            for (var t : portfolio.getTransactions()) {
                outputArea.append(t.toString() + "\n");
            }
        });
    }

    public static void main(String[] args) {
        new TradingGUI().setVisible(true);
    }
}
