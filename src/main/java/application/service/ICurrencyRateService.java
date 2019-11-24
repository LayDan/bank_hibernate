package application.service;

import application.domain.Rates;

public interface ICurrencyRateService {
    Rates add(String first, String second, double x);

    Iterable<Rates> findAll();
}
