package myApplication.controller;

import myApplication.domain.Role;
import myApplication.domain.User;
import myApplication.repos.UserRepository;
import myApplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/usersList")
//@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public String accountList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "usersList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "usersEdit";
    }

    @PostMapping
    public String editAccountRole(
            @RequestParam String username,
            @RequestParam Map<String, String> formRole,
            @RequestParam(name = "userId") User user, Model model) {
        iUserService.editUser(user, username, formRole);
        return "redirect:/usersList";

    }


}
