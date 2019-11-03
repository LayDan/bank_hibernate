package myApplication.controller;

import myApplication.domain.Account;
import myApplication.domain.Role;
import myApplication.repos.AccountRepo;
import myApplication.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepo accountRepo;

    @GetMapping
    public String accountList(Model model) {
        model.addAttribute("accounts", accountRepo.findAll());
        return "accountList";
    }

    @GetMapping("{account}")
    public String userEditForm(@PathVariable Account account, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("roles", Role.values());
        return "accountEdit";
    }

    @PostMapping
    public String saveAccount(
            @RequestParam String username,
            @RequestParam Map<String, String> formRole,
            @RequestParam(name = "accountId") Account account, Model model) {

        account.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        account.getRoles().clear();

        for (String key : formRole.keySet()) {
            if (roles.contains(key)) {
                account.getRoles().add(Role.valueOf(key));
            }
        }
        accountRepo.save(account);
        return "redirect:/account";

    }


}
