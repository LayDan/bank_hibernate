package myApplication.service;

import myApplication.domain.Currency;

public interface ICurrencyService {
    void addCurrency(Currency currency);

    void delete(long id);

    Iterable<Currency> findAll();


}
