package vish.thinkhub.service;



import vish.thinkhub.model.StockQuote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalStockApiService {

    private final WebClient webClient;
    private final String apiKey;

    public ExternalStockApiService(WebClient.Builder webClientBuilder, @Value("${alphavantage.api.key}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl("https://www.alphavantage.co").build();
        this.apiKey = apiKey;
    }

    public StockQuote fetchQuote(String symbol) {
        // Implement the logic to fetch stock quote from Alpha Vantage API
        // Parse the response and create a StockQuote object
        // Handle any errors and throw appropriate exceptions

    }
}