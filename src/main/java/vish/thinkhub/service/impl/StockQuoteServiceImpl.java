package vish.thinkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vish.thinkhub.model.StockQuote;
import vish.thinkhub.service.ExternalStockApiService;
import vish.thinkhub.service.StockQuoteService;
import vish.thinkhub.exception.StockQuoteException;
import vish.thinkhub.exception.ExternalApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockQuoteServiceImpl implements StockQuoteService {

    private static final Logger logger = LoggerFactory.getLogger(StockQuoteServiceImpl.class);

    private final ExternalStockApiService externalStockApiService;

    @Autowired
    public StockQuoteServiceImpl(ExternalStockApiService externalStockApiService) {
        this.externalStockApiService = externalStockApiService;
    }

    @Override
    @Cacheable(value = "stockQuotes", key = "#symbol")
    public StockQuote getQuoteBySymbol(String symbol) {
        logger.info("Fetching stock quote for symbol: {}", symbol);
        try {
            StockQuote quote = externalStockApiService.fetchQuote(symbol);
            if (quote == null) {
                throw new StockQuoteException("No quote found for symbol: " + symbol);
            }
            return quote;
        } catch (ExternalApiException e) {
            logger.error("Error fetching quote from external API for symbol: {}", symbol, e);
            throw new StockQuoteException("Failed to fetch quote for symbol: " + symbol, e);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching quote for symbol: {}", symbol, e);
            throw new StockQuoteException("An unexpected error occurred while fetching quote for symbol: " + symbol, e);
        }
    }

    @Override
    public List<StockQuote> getBatchQuotes(List<String> symbols) {
        logger.info("Fetching batch quotes for symbols: {}", symbols);
        return symbols.stream()
                .map(this::getQuoteBySymbol)
                .collect(Collectors.toList());
    }
}