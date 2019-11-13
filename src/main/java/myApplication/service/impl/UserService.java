package myApplication.service.impl;

import myApplication.domain.Bill;
import myApplication.domain.Role;
import myApplication.domain.Transaction;
import myApplication.domain.User;
import myApplication.repos.TransactionRepos;
import myApplication.repos.UserRepository;
import myApplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepos transactionRepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        ////////////////////////////////////////
        Transaction transaction = new Transaction();
        transaction.setUser_id(user.getId());
        transaction.setMessage("New user " + user.getUsername());
        transactionRepos.saveAndFlush(transaction);
        ///////////////////////////////////////////////////////
        return userRepository.saveAndFlush(user);
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
        return user.getBills();
    }
}
