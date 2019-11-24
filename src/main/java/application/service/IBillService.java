package application.service;

import application.domain.Bill;
import application.domain.Currency;
import application.domain.User;

public interface IBillService {
    User addBill(String currency, double amount, User user);

    void delete(long id);

    void addMoney(long id, double amoung);

    void transfer(long numberCard, double amoung, long selfNumberCard, User user);

    void convertToNewCurrency(long numberCard, String currency);

    double convertMoney(Bill bill, Currency currency, double amoung);

    double convertAllMoneyToUAH(User user);
}
