package application.controller;

import application.domain.User;
import application.service.IBillService;
import application.service.ICurrencyService;
import application.service.IUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class BillController {

    String userInfo = "userInfo";

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

        model.addAttribute(userInfo, iUserService.getCurrentUser());
        model.addAttribute("bills", iUserService.getAllBill(user));

        return "account";
    }

    @GetMapping("/addMoney")
    public String getMoney(Model model, @AuthenticationPrincipal Object user) {
        model.addAttribute(userInfo, (User) user);
        return "addMoney";
    }

    @PostMapping("/addMoney")
    public String addMoney(@AuthenticationPrincipal Object user, Model model, @RequestParam long numberCard, @RequestParam double amoung) {
        iBillService.addMoney(numberCard, amoung);
        model.addAttribute(userInfo, user);
        model.addAttribute("bills", iUserService.getAllBill((User) user));
        return "account";
    }


}
