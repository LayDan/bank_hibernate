package myApplication.service;

import myApplication.domain.Rates;

public interface ICurrencyRateService {
    void add(String first, String second, double x);

    Iterable<Rates> findAll();
}
