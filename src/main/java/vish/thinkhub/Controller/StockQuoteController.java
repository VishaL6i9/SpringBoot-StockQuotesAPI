package vish.thinkhub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vish.thinkhub.model.StockQuote;
import vish.thinkhub.service.StockQuoteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-quotes")
public class StockQuoteController {

    private final StockQuoteService stockQuoteService;
    private final CacheManager cacheManager;

    @Autowired
    public StockQuoteController(StockQuoteService stockQuoteService, CacheManager cacheManager) {
        this.stockQuoteService = stockQuoteService;
        this.cacheManager = cacheManager;
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<StockQuote> getQuoteBySymbol(@PathVariable String symbol) {
        StockQuote quote = stockQuoteService.getQuoteBySymbol(symbol);
        return ResponseEntity.ok(quote);
    }

    @GetMapping("/batch")
    public ResponseEntity<List<StockQuote>> getBatchQuotes(@RequestParam List<String> symbols) {
        List<StockQuote> quotes = stockQuoteService.getBatchQuotes(symbols);
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/cache-stats")
    public ResponseEntity<String> getCacheStats() {
        if (cacheManager instanceof CaffeineCacheManager) {
            CaffeineCacheManager caffeineCacheManager = (CaffeineCacheManager) cacheManager;
            com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache =
                    (com.github.benmanes.caffeine.cache.Cache<Object, Object>) caffeineCacheManager.getCache("stockQuotes").getNativeCache();
            return ResponseEntity.ok(nativeCache.stats().toString());
        }
        return ResponseEntity.ok("Cache statistics not available");
    }
}