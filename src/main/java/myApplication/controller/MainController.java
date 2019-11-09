package myApplication.controller;

import myApplication.domain.CurrencyRate;
import myApplication.service.ICurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ICurrencyRateService iCurrencyRateService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "main";
    }

    @GetMapping("/currencyRate")
    public String currencyRate(Model model) {
        model.addAttribute("currencyRates", iCurrencyRateService.findAll());
        return "currencyRate";
    }

    @GetMapping("/addCurrency")
    public String addCurrency(Model model) {
        return "addCurrency";
    }

    @PostMapping("/addCurrency")
    public String add(Model model, CurrencyRate currencyRate) {
        iCurrencyRateService.add(currencyRate);
        model.addAttribute("currencyRates", iCurrencyRateService.findAll());
        return "currencyRate";
    }


}