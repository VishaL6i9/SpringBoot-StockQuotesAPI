package vish.thinkhub.service.impl;

import org.springframework.stereotype.Service;
import vish.thinkhub.model.StockQuote;
import vish.thinkhub.service.StockQuoteService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockQuoteServiceImpl implements StockQuoteService {

    @Override
    public StockQuote getQuoteBySymbol(String symbol) {
        // TO DO: implement the logic to retrieve the stock quote by symbol
        // For now, let's just return a dummy stock quote
        StockQuote stockQuote = new StockQuote(symbol, BigDecimal.valueOf(100.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), LocalDateTime.now());
        return stockQuote;
    }

    @Override
    public List<StockQuote> getBatchQuotes(List<String> symbols) {
        // TO DO: implement the logic to retrieve the batch quotes by symbols
        // For now, let's just return a dummy list of stock quotes
        List<StockQuote> stockQuotes = new ArrayList<>();
        for (String symbol : symbols) {
            StockQuote stockQuote = new StockQuote(symbol, BigDecimal.valueOf(100.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), LocalDateTime.now());
            stockQuotes.add(stockQuote);
        }
        return stockQuotes;
    }
}
