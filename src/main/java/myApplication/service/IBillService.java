package myApplication.service;

import myApplication.domain.Bill;
import myApplication.domain.User;

public interface IBillService {
    User addBill(Bill bill, User user);

    void delete(long id);

    Bill edit(Bill bill);

    Bill findByid(long id);
}
