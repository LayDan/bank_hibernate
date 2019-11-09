package myApplication.controller;

import myApplication.domain.CurrencyRate;
import myApplication.repos.UserRepository;
import myApplication.service.impl.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "main";
    }

    @GetMapping("/currencyRate")
    public String currencyRate(Map<String, Object> model) {

        return "currencyRate";
    }
    @GetMapping("/addCurrency")
    public String addCurrency(Model model){

        return "addCurrency";
    }

    @PostMapping("/addCurrency")
    public String add(Model model, CurrencyRate currencyRate){

        return "addCurrency";
    }



}