package application.controller;

import application.domain.User;
import application.service.IBillService;
import application.service.ICurrencyService;
import application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IBillService iBillService;
    @Autowired
    private ICurrencyService iCurrencyService;

    String userInfo = "userInfo";
    String currencys = "currencys";
    String bills = "bills";
    String numberOfCards = "numberOfCards";
    String account = "account";


    @GetMapping("/account")
    public String accountmain(Model model, @AuthenticationPrincipal Object user) {
        model.addAttribute(userInfo, (User) user);
        model.addAttribute(bills, iUserService.getAllBill((User) user));
        return account;
    }

    ///////////////////////////////////////////add money
    ////////////////////////////////////////////////////Transfer
    @GetMapping("/transfer")
    public String transfer(Model model, @AuthenticationPrincipal Object user) {
        model.addAttribute(numberOfCards, ((User) user).getBills());
        model.addAttribute("user", user);
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transferMoney(@AuthenticationPrincipal Object user, Model model,
                                @RequestParam long numberCard,
                                @RequestParam long selfNumberCard,
                                @RequestParam double amoung
    ) {
        iBillService.transfer(numberCard, amoung, selfNumberCard, (User) user);
        model.addAttribute(numberOfCards, ((User) user).getBills());
        model.addAttribute(userInfo, user);
        model.addAttribute(bills, iUserService.getAllBill((User) user));
        return account;
    }

    ///////////////////////////////////////////////////Convert
    @GetMapping("/convert")
    public String convert(Model model, @AuthenticationPrincipal Object user) {
        model.addAttribute(numberOfCards, ((User) user).getBills());
        model.addAttribute("user", user);
        model.addAttribute(currencys, iCurrencyService.findAll());
        return "convert";
    }

    @PostMapping("/convert")
    public String convertMoney(@AuthenticationPrincipal Object user, Model model,
                               @RequestParam long numberCard,
                               @RequestParam String currency
    ) {
        iBillService.convertToNewCurrency(numberCard, currency);

        model.addAttribute(currencys, iCurrencyService.findAll());
        model.addAttribute(userInfo, user);
        model.addAttribute(bills, iUserService.getAllBill((User) user));
        return "redirect:/account";
    }

    ///////////////////////////////////////////////////////convertAllToUAH
    @GetMapping("/convertAllToUAH")
    public String convertAllToUAH(Model model, @AuthenticationPrincipal Object user) {
        model.addAttribute("amoung", iBillService.convertAllMoneyToUAH((User) user));
        model.addAttribute(userInfo, user);
        model.addAttribute(currencys, iCurrencyService.findAll());
        model.addAttribute(bills, iUserService.getAllBill((User) user));
        return account;
    }

}
