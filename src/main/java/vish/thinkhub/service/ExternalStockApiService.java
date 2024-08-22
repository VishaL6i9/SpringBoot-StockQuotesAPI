package vish.thinkhub.service;





import com.fasterxml.jackson.databind.JsonNode;
import vish.thinkhub.exception.ExternalApiException;
import vish.thinkhub.model.StockQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ExternalStockApiService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalStockApiService.class);

    private final WebClient webClient;
    private final String apiKey;

    public ExternalStockApiService(WebClient.Builder webClientBuilder,
                                   @Value("${alphavantage.api.key}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl("https://www.alphavantage.co").build();
        this.apiKey = apiKey;
    }

    public StockQuote fetchQuote(String symbol) {
        logger.info("Fetching quote for symbol: {}", symbol);

        String url = "/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .flatMap(this::parseResponse)
                .block();
    }

    private Mono<StockQuote> parseResponse(JsonNode response) {
        JsonNode quoteNode = response.get("Global Quote");

        if (quoteNode == null || quoteNode.isEmpty()) {
            return Mono.error(new ExternalApiException("Invalid response from Alpha Vantage API"));
        }

        try {
            String symbol = quoteNode.get("01. symbol").asText();
            BigDecimal price = new BigDecimal(quoteNode.get("05. price").asText());
            BigDecimal change = new BigDecimal(quoteNode.get("09. change").asText());
            BigDecimal percentageChange = new BigDecimal(quoteNode.get("10. change percent").asText().replace("%", ""));

            StockQuote quote = new StockQuote(symbol, price, change, percentageChange, LocalDateTime.now());
            return Mono.just(quote);
        } catch (Exception e) {
            logger.error("Error parsing API response", e);
            return Mono.error(new ExternalApiException("Error parsing API response", e));
        }
    }
}