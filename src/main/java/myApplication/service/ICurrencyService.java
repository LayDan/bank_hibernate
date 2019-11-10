package myApplication.service;

import myApplication.domain.Currency;

public interface ICurrencyService {
    Currency addCurrency(Currency currency);

    void delete(long id);

    Iterable<Currency> findAll();


}
