package vish.thinkhub.service;



import vish.thinkhub.model.StockQuote;

import java.util.List;

public interface StockQuoteService {
    StockQuote getQuoteBySymbol(String symbol);
    List<StockQuote> getBatchQuotes(List<String> symbols);
}