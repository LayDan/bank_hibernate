package application.service.impl;

import application.domain.Currency;
import application.domain.Rates;
import application.repos.CurrencyRateRepos;
import application.repos.CurrencyRepository;
import application.service.ICurrencyRateService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRateService implements ICurrencyRateService {

    private CurrencyRateRepos currencyRateRepos;

    private CurrencyRepository currencyRepository;

    public CurrencyRateService(CurrencyRateRepos currencyRateRepos, CurrencyRepository currencyRepository) {
        this.currencyRateRepos = currencyRateRepos;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void add(String first, String second, double x) {
        boolean b = false;
        if (!first.equals(second) && x != 0 && x > 0 && currencyRateRepos.findAll().isEmpty()) {
            Currency currencyFirst = currencyRepository.findByValute(first);
            Currency currencySecond = currencyRepository.findByValute(second);
            Rates rates = new Rates(currencyFirst, currencySecond, x);
            currencyRateRepos.saveAndFlush(rates);
        } else if (!currencyRateRepos.findAll().isEmpty()) {
            Currency currencyFirst = currencyRepository.findByValute(first);
            Currency currencySecond = currencyRepository.findByValute(second);
            for (Rates a : currencyRateRepos.findAll()) {
                if (a.getFirst().getValute().equals(first) && a.getSecond().getValute().equals(second) && a.getX() == x) {
                    b = true;
                }
            }
            if (!b) {
                Rates rates = new Rates(currencyFirst, currencySecond, x);
                currencyRateRepos.saveAndFlush(rates);
            }
        }

    }

    @Override
    public Iterable<Rates> findAll() {
        return currencyRateRepos.findAll();
    }
}
