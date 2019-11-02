package myApplication.controller;

import myApplication.domain.Account;
import myApplication.domain.Role;
import myApplication.domain.User;
import myApplication.repos.AccountRepo;
import myApplication.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepo accountRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(Account account, User user, Map<String, Object> model) {
        Account accountFromDb = accountRepo.findByUsername(account.getUsername());
        if (accountFromDb != null) {
            model.put("infoAboutAccount", "User exists!");
            return "registration";
        }
        account.setActive(true);
        account.setRoles(Collections.singleton(Role.USER));
        accountRepo.save(account);
        userRepository.save(user);
        return "redirect:/login";
    }
}
