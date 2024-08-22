package vish.thinkhub.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockQuote {
    private String symbol;
    private BigDecimal price;
    private BigDecimal change;
    private BigDecimal percentageChange;
    private LocalDateTime timestamp;

    public StockQuote(String symbol, BigDecimal price, BigDecimal change, BigDecimal percentageChange, LocalDateTime timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.change = change;
        this.percentageChange = percentageChange;
        this.timestamp = timestamp;
    }

    // Getters and setters
}