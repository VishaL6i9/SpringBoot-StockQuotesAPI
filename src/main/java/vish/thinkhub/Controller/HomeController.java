package vish.thinkhub.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/api/v1/stock-quotes";
    }
    @GetMapping("/error")
    public String after_login() {
        return "redirect:/api/v1/stock-quotes";
    }
}