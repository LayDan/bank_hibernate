package myApplication.controller;

import myApplication.domain.Bill;
import myApplication.domain.User;
import myApplication.service.IBillService;
import myApplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addbill")
public class BillController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IBillService iBillService;


    @GetMapping()
    public String account(Model model) {
        model.addAttribute("user",iUserService.getCurrentUser());
        return "addbill";
    }

    @PostMapping("/addbill")
    public String addBill(Bill bill, @RequestParam(name = "user_id") User user, Model model) {
        iBillService.addBill(bill);
        model.addAttribute("user", user.getBills());

        return "account";
    }
}
