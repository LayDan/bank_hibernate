package myApplication.service;

import myApplication.domain.Bill;

public interface IBillService {
    Bill addBill(Bill bill);

    void delete(long id);

    Bill edit(Bill bill);

    Bill findByid(long id);
}
