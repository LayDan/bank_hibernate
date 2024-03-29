package application.controller;

import application.domain.Currency;
import application.service.ICurrencyRateService;
import application.service.ICurrencyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    String currencys = "currencys";


    private ICurrencyRateService iCurrencyRateService;

    private ICurrencyService iCurrencyService;

    public MainController(ICurrencyRateService iCurrencyRateService, ICurrencyService iCurrencyService) {
        this.iCurrencyRateService = iCurrencyRateService;
        this.iCurrencyService = iCurrencyService;
    }

    //////////////////////////////////main
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "main";
    }

    ///////////////////////////////////////////////////
    @GetMapping("/currencyRate")
    public String currencyRate(Model model) {
        model.addAttribute("currencyRates", iCurrencyRateService.findAll());
        return "currencyRate";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addCurrencyRate")
    public String addCurrencyRate(Model model) {
        model.addAttribute(currencys, iCurrencyService.findAll());
        return "addCurrencyRate";
    }

    @PostMapping("/addCurrencyRate")
    public String add(Model model, @RequestParam String first, @RequestParam String second, @RequestParam double x) {
        iCurrencyRateService.add(first, second, x);
        model.addAttribute(currencys, iCurrencyService.findAll());
        model.addAttribute("currencyRates", iCurrencyRateService.findAll());
        return "currencyRate";
    }


    /////////////////////////////////////////////////////////////////////////Currency
    @GetMapping("/addCurrency")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addCurrency(Model model) {
        return "addCurrency";
    }

    @PostMapping("/addCurrency")
    public String addCurrency(Model model, Currency currency) {
        iCurrencyService.addCurrency(currency);
        model.addAttribute(currencys, iCurrencyService.findAll());

        return "currency";
    }

    @GetMapping("/currency")
    public String watchCurrency(Model model) {
        model.addAttribute("currencys", iCurrencyService.findAll());
        return "currency";
    }


}