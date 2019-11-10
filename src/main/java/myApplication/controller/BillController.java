package myApplication.controller;

import myApplication.domain.Bill;
import myApplication.domain.Currency;
import myApplication.domain.User;
import myApplication.service.IBillService;
import myApplication.service.ICurrencyService;
import myApplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addbill")
public class BillController {

    private IUserService iUserService;

    private IBillService iBillService;

    private ICurrencyService iCurrencyService;

    public BillController(IUserService iUserService, IBillService iBillService, ICurrencyService iCurrencyService) {
        this.iUserService = iUserService;
        this.iBillService = iBillService;
        this.iCurrencyService = iCurrencyService;
    }


    @GetMapping()
    public String account(Model model) {
        model.addAttribute("user", iUserService.getCurrentUser());
        model.addAttribute("currencys", iCurrencyService.findAll());
        return "addbill";
    }

    @PostMapping()
    public String addBill(Bill bill, Model model) {
        User user = iUserService.getCurrentUser();
        iBillService.addBill(bill, user);
        model.addAttribute("bills", iUserService.getAllBill(user));

        return "account";
    }
}
