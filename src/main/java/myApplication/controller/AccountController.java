package myApplication.controller;

import myApplication.domain.User;
import myApplication.service.IBillService;
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


    @GetMapping("/account")
    public String accountmain(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("bills", iUserService.getAllBill(user));
        return "account";
    }

    ///////////////////////////////////////////add money
    @GetMapping("/addMoney")
    public String Money(Model model, @AuthenticationPrincipal User user) {
        return "addMoney";
    }

    @PostMapping("/addMoney")
    public String addMoney(@AuthenticationPrincipal User user, Model model, @RequestParam long number_card, @RequestParam double amoung) {
        User user1 = iBillService.addMoney(number_card, amoung);
        if (user1.getUsername().equals(user.getUsername())) {
            model.addAttribute("bills", iUserService.getAllBill(user1));
        } else {
            model.addAttribute("bills", iUserService.getAllBill(user));
        }
        return "account";
    }


}
