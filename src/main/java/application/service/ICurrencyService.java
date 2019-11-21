package application.service;

import application.domain.Currency;

public interface ICurrencyService {
    Currency addCurrency(Currency currency);

    void delete(long id);

    Iterable<Currency> findAll();


}
