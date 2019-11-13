package myApplication.service.impl;

import myApplication.domain.Currency_rate;
import myApplication.repos.CurrencyRateRepos;
import myApplication.service.ICurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRateService implements ICurrencyRateService {
    @Autowired
    private CurrencyRateRepos currencyRateRepos;

    @Override
    public Currency_rate add(Currency_rate currencyRate) {
        return currencyRateRepos.saveAndFlush(currencyRate);
    }

    @Override
    public Iterable<Currency_rate> findAll() {
        return currencyRateRepos.findAll();
    }
}
