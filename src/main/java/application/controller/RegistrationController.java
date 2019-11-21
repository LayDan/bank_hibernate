package application.controller;

import application.domain.User;
import application.repos.UserRepository;
import application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userServiceinterface;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(Object user, Map<String, Object> model) {
        if (userServiceinterface.findByUsername(((User)user).getUsername())) {
            model.put("infoAboutAccount", "User exists!");
            return "registration";
        } else {
            userServiceinterface.addUser((User)user);
            return "redirect:/login";
        }
    }
}
