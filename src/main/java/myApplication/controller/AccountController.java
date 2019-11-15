package myApplication.controller;

import myApplication.domain.User;
import myApplication.service.IBillService;
import myApplication.service.ICurrencyService;
import myApplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IBillService iBillService;
    @Autowired
    private ICurrencyService iCurrencyService;


    @GetMapping("/account")
    public String accountmain(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("userInfo", user);
        model.addAttribute("bills", iUserService.getAllBill(user));
        return "account";
    }

    ///////////////////////////////////////////add money
    ////////////////////////////////////////////////////Transfer
    @GetMapping("/transfer")
    public String transfer(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("numberOfCards", user.getBills());
        model.addAttribute("user", user);
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transferMoney(@AuthenticationPrincipal User user, Model model,
                                @RequestParam long number_card,
                                @RequestParam long self_number_card,
                                @RequestParam double amoung
    ) {
        iBillService.transfer(number_card, amoung, self_number_card, user);
        model.addAttribute("numberOfCards", user.getBills());
        model.addAttribute("userInfo", user);
        model.addAttribute("bills", iUserService.getAllBill(user));
        return "account";
    }

    ///////////////////////////////////////////////////Convert
    @GetMapping("/convert")
    public String convert(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("numberOfCards", user.getBills());
        model.addAttribute("user", user);
        model.addAttribute("currencys", iCurrencyService.findAll());
        return "convert";
    }

    @PostMapping("/convert")
    public String convertMoney(@AuthenticationPrincipal User user, Model model,
                               @RequestParam long number_card,
                               @RequestParam String currency
    ) {
        iBillService.convert_to_newCurrency(number_card, currency);

        model.addAttribute("currencys", iCurrencyService.findAll());
        model.addAttribute("userInfo", user);
        model.addAttribute("bills", iUserService.getAllBill(user));
        return "account";
    }

    ///////////////////////////////////////////////////////convertAllToUAH
    @GetMapping("/convertAllToUAH")
    public String convertAllToUAH(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("amoung", iBillService.convertAllMoneyToUAH(user));
        model.addAttribute("userInfo", user);
        model.addAttribute("currencys", iCurrencyService.findAll());
        model.addAttribute("bills", iUserService.getAllBill(user));
        return "account";
    }

}
