package application.service.impl;

import application.domain.Bill;
import application.domain.Role;
import application.domain.Transaction;
import application.domain.User;
import application.repos.TransactionRepos;
import application.repos.UserRepository;
import application.service.IUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, IUserService {


    private UserRepository userRepository;

    private TransactionRepos transactionRepos;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, TransactionRepos transactionRepos, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.transactionRepos = transactionRepos;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
        ////////////////////////////////////////
        Transaction transaction = new Transaction();
        transaction.setMessage("New user ");
        transaction.setUserId(user.getId());
        transactionRepos.saveAndFlush(transaction);
        ///////////////////////////////////////////////////////
        return user;
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User editUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        /////////////////////////////////////////////////
        Transaction transaction = new Transaction();
        transaction.setUserId(user.getId());
        transaction.setMessage("User edit " + user.getUsername());
        transactionRepos.saveAndFlush(transaction);
        /////////////////////////////////////////////////
        return userRepository.save(user);
    }

    @Override
    public boolean findByUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    @Transactional
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @Override
    @Transactional
    public Iterable<Bill> getAllBill(User user) {
        User user1 = userRepository.findByUsername(user.getUsername());
        return user1.getBills();
    }
}