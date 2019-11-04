package myApplication.service;

import myApplication.domain.Bill;
import myApplication.domain.User;

import java.util.Map;

public interface IUserService {
    User addUser(User user);

    void deleteUserById(long id);

    User editUser(User user, String username, Map<String, String> form);

    boolean findByUsername(String username);

    User getCurrentUser();

    Iterable<Bill> getAllBill(User user);

}
