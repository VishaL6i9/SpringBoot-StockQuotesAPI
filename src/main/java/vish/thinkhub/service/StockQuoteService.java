package vish.thinkhub.service;



import org.springframework.stereotype.Service;
import vish.thinkhub.model.StockQuote;

import java.util.List;
@Service
public interface StockQuoteService {
    StockQuote getQuoteBySymbol(String symbol);
    List<StockQuote> getBatchQuotes(List<String> symbols);
}