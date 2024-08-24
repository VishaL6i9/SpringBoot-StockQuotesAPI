package vish.thinkhub.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> handleErrorJson(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        String path = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        Map<String, Object> body = Map.of(
                "type", "about:blank",
                "title", status.getReasonPhrase(),
                "status", status.value(),
                "detail", getErrorMessage(status, path),
                "instance", path
        );

        return new ResponseEntity<>(body, status);
    }

    @RequestMapping(value = "/error", produces = MediaType.TEXT_HTML_VALUE)
    public String handleErrorHtml(HttpServletRequest request, Model model) {
        HttpStatus status = getStatus(request);
        String path = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        model.addAttribute("errorMsg", getErrorMessage(status, path));
        model.addAttribute("statusCode", status.value());

        return "error";
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    private String getErrorMessage(HttpStatus status, String path) {
        if (status == HttpStatus.NOT_FOUND) {
            return "No static resource " + path + ". Please use /api/v1/stock-quotes/{SYMBOL} for stock quotes.";
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
            return "An internal server error occurred";
        } else {
            return "An error occurred";
        }
    }
}