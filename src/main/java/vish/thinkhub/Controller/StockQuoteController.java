package vish.thinkhub.Controller;



import vish.thinkhub.model.StockQuote;
import vish.thinkhub.service.StockQuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-quotes")
public class StockQuoteController {

    private final StockQuoteService stockQuoteService;

    public StockQuoteController(StockQuoteService stockQuoteService) {
        this.stockQuoteService = stockQuoteService;
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
}