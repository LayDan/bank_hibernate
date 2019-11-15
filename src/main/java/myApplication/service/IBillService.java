package myApplication.service;

import myApplication.domain.Bill;
import myApplication.domain.Currency;
import myApplication.domain.User;

public interface IBillService {
    User addBill(String currency, double amount, User user);

    void delete(long id);

    Bill edit(Bill bill);

    Bill findByid(long id);

    User addMoney(long id, double amoung);

    void transfer(long number_card, double amoung, long self_number_card, User user);

    void convert_to_newCurrency(long number_card, String currency);

    double convertMoney(Bill bill, Currency currency, double amoung);

    double convertAllMoneyToUAH(User user);
}
