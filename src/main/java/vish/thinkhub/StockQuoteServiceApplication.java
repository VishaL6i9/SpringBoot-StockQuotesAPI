package vish.thinkhub;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vish.thinkhub.service.StockQuoteService;

@SpringBootApplication(scanBasePackages = "vish.thinkhub")
public class StockQuoteServiceApplication {
 /*   @Bean
    public StockQuoteService stockQuoteService() {
        return new StockQuoteService();
    } */
    public static void main(String[] args) {
        SpringApplication.run(StockQuoteServiceApplication.class, args);
    }

}
