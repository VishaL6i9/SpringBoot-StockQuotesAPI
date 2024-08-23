package vish.thinkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vish.thinkhub.model.StockQuote;
import vish.thinkhub.service.ExternalStockApiService;
import vish.thinkhub.service.StockQuoteService;


import java.util.ArrayList;
import java.util.List;

@Service
public class StockQuoteServiceImpl implements StockQuoteService {

    private final ExternalStockApiService externalStockApiService;

    @Autowired
    public StockQuoteServiceImpl(ExternalStockApiService externalStockApiService) {
        this.externalStockApiService = externalStockApiService;
    }

    @Override
    @Cacheable(value = "stockQuotes", key = "#symbol")
    public StockQuote getQuoteBySymbol(String symbol) {
        return externalStockApiService.fetchQuote(symbol);
    }

    @Override
    public List<StockQuote> getBatchQuotes(List<String> symbols) {
        List<StockQuote> stockQuotes = new ArrayList<>();
        for (String symbol : symbols) {
            StockQuote stockQuote = externalStockApiService.fetchQuote(symbol);
            stockQuotes.add(stockQuote);
        }
        return stockQuotes;
    }
}
