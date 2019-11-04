package myApplication.controller;

import myApplication.service.IBillService;
import myApplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IBillService iBillService;


    @GetMapping
    public String accountmain(Model model) {
        model.addAttribute("bills", iUserService.getAllBill(iUserService.getCurrentUser()));
        return "account";
    }

}
