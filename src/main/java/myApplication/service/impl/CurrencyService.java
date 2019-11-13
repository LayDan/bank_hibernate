package myApplication.service.impl;

import myApplication.domain.Currency;
import myApplication.repos.CurrencyRepository;
import myApplication.service.ICurrencyService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService implements ICurrencyService {

    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency addCurrency(Currency currency) {
        if (currency.getValue().length() > 3) {
            return null;
        }
        boolean yes = false;
        if (currencyRepository.findAll() == null) {
            return currencyRepository.saveAndFlush(currency);
        } else {
            for (Currency currency1 : currencyRepository.findAll()) {
                if (currency1.getValue().equals(currency.getValue())) {
                    yes = true;
                }
            }
        }
        if (yes) {
            return null;
        } else {
            return currencyRepository.saveAndFlush(currency);
        }
    }

    @Override
    public void delete(long id) {
        if (currencyRepository.findById(id).isPresent()) {
            currencyRepository.deleteById(id);
        }
    }

    @Override
    public Iterable<Currency> findAll() {
        return currencyRepository.findAll();
    }
}
