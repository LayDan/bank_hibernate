package myApplication.service.impl;

import myApplication.domain.CurrencyRate;
import myApplication.repos.CurrencyRateRepos;
import myApplication.service.ICurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRateService implements ICurrencyRateService {
    @Autowired
    private CurrencyRateRepos currencyRateRepos;

    @Override
    public CurrencyRate add(CurrencyRate currencyRate) {
        return currencyRateRepos.saveAndFlush(currencyRate);
    }

    @Override
    public Iterable<CurrencyRate> findAll() {
        return currencyRateRepos.findAll();
    }
}
