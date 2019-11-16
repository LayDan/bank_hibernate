package myApplication.service.impl;

import myApplication.domain.Bill;
import myApplication.domain.Role;
import myApplication.domain.Transaction;
import myApplication.domain.User;
import myApplication.repos.TransactionRepos;
import myApplication.repos.UserRepository;
import myApplication.service.IUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, IUserService {

    private UserRepository userRepository;

    private TransactionRepos transactionRepos;

    public UserService(UserRepository userRepository, TransactionRepos transactionRepos) {
        this.userRepository = userRepository;
        this.transactionRepos = transactionRepos;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.saveAndFlush(user);
        ////////////////////////////////////////
        Transaction transaction = new Transaction();
        transaction.setMessage("New user ");
        transaction.setUser_id(user.getId());
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
        transaction.setUser_id(user.getId());
        transaction.setMessage("User edit " + user.getUsername());
        transactionRepos.saveAndFlush(transaction);
        /////////////////////////////////////////////////
        return userRepository.save(user);
    }

    @Override
    public boolean findByUsername(String username) {
        if (userRepository.findByUsername(username) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Iterable<Bill> getAllBill(User user) {
        User user1 = userRepository.findByUsername(user.getUsername());
        String s = "";
        return user1.getBills();
    }
}
