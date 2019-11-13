package myApplication.service;

import myApplication.domain.Currency_rate;

public interface ICurrencyRateService {
    Currency_rate add(Currency_rate currencyRate);

    Iterable<Currency_rate> findAll();
}
