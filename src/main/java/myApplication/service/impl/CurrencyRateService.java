package myApplication.service.impl;

import myApplication.domain.Currency;
import myApplication.domain.Rates;
import myApplication.repos.CurrencyRateRepos;
import myApplication.repos.CurrencyRepository;
import myApplication.service.ICurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRateService implements ICurrencyRateService {
    @Autowired
    private CurrencyRateRepos currencyRateRepos;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public void add(String first, String second, double x) {
        boolean b = false;
        if (!first.equals(second) && x != 0 && x > 0) {
            Currency currency_first = currencyRepository.findByValute(first);
            Currency currency_second = currencyRepository.findByValute(second);

            if (currencyRateRepos.findAll().isEmpty()) {
                Rates rates = new Rates(currency_first, currency_second, x);
                currencyRateRepos.saveAndFlush(rates);
            } else {
                for (Rates a : currencyRateRepos.findAll()) {
                    if (a.getFirst().getValue().equals(first) && a.getSecond().getValue().equals(second) && a.getX() == x) {
                        b = true;
                    }
                }
                if (!b) {
                    Rates rates = new Rates(currency_first, currency_second, x);
                    currencyRateRepos.saveAndFlush(rates);
                }
            }
        }
    }

    @Override
    public Iterable<Rates> findAll() {
        return currencyRateRepos.findAll();
    }
}
