package vish.thinkhub.exception;

public class StockQuoteException extends RuntimeException {
    public StockQuoteException(String message) {
        super(message);
    }

    public StockQuoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockQuoteException(Throwable cause) {
        super(cause);
    }
}
