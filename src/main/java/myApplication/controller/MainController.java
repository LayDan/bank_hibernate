package myApplication.controller;

import myApplication.domain.User;
import myApplication.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String FirstName, @RequestParam String SecondName, @RequestParam int Age, Map<String, Object> model) {
        User user = new User(Age, FirstName, SecondName);
        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }
//    @GetMapping("/login")
//    public String login(Map<String, Object> model) {
//        return "login";
//    }


}