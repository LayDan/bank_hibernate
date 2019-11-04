package myApplication.controller;

import myApplication.domain.User;
import myApplication.repos.UserRepository;
import myApplication.service.IUserService;
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
    public String add(User user, Map<String, Object> model) {
        if (userServiceinterface.findByUsername(user.getUsername())) {
            model.put("infoAboutAccount", "User exists!");
            return "registration";
        } else {
            userServiceinterface.addUser(user);
            return "redirect:/login";
        }
    }
}
