package myApplication.controller;

import myApplication.domain.User;
import myApplication.service.IBillService;
import myApplication.service.ICurrencyService;
import myApplication.service.IUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class BillController {

    private IUserService iUserService;

    private IBillService iBillService;

    private ICurrencyService iCurrencyService;

    public BillController(IUserService iUserService, IBillService iBillService, ICurrencyService iCurrencyService) {
        this.iUserService = iUserService;
        this.iBillService = iBillService;
        this.iCurrencyService = iCurrencyService;
    }

    @GetMapping("/addbill")
    public String account(Model model) {

        model.addAttribute("currencys", iCurrencyService.findAll());
        model.addAttribute("user", iUserService.getCurrentUser());
        return "addbill";
    }

    @PostMapping("/addbill")
    public String addBill(@RequestParam String currency, @RequestParam double amoung, Model model) {
        User user = iUserService.getCurrentUser();
        iBillService.addBill(currency, amoung, user);

        model.addAttribute("userInfo", iUserService.getCurrentUser());
        model.addAttribute("bills", iUserService.getAllBill(user));

        return "account";
    }

    @GetMapping("/addMoney")
    public String Money(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("userInfo", user);
        return "addMoney";
    }

    @PostMapping("/addMoney")
    public String addMoney(@AuthenticationPrincipal User user, Model model, @RequestParam long number_card, @RequestParam double amoung) {
        iBillService.addMoney(number_card, amoung);
        model.addAttribute("userInfo", user);
        model.addAttribute("bills", iUserService.getAllBill(user));
        return "account";
    }


}
