package myApplication.service;

import myApplication.domain.CurrencyRate;

public interface ICurrencyRateService {
    CurrencyRate add(CurrencyRate currencyRate);

    Iterable<CurrencyRate> findAll();
}
