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

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getChange() {
        return change;
    }

    public BigDecimal getPercentageChange() {
        return percentageChange;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public void setPercentageChange(BigDecimal percentageChange) {
        this.percentageChange = percentageChange;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
