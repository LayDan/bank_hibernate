package application.service.impl;

import application.domain.Currency;
import application.repos.CurrencyRepository;
import application.service.ICurrencyService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService implements ICurrencyService {

    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency addCurrency(Currency currency) {
        boolean yes = true;
        boolean cheek = cheek(currency);
        if (cheek && currencyRepository.findAll().isEmpty()) {
            currencyRepository.saveAndFlush(currency);
            return currency;
        } else if (cheek && !currencyRepository.findAll().isEmpty()) {
            for (Currency currency1 : currencyRepository.findAll()) {
                if (currency1.getValute().equals(currency.getValute())) {
                    yes = false;
                    break;
                }
            }
        }
        if (cheek && yes) {
            currencyRepository.saveAndFlush(currency);
            return currency;
        }
        return null;
    }

    public boolean cheek(Currency currency) {
        int cheek = 0;
        if (currency.getValute().length() == 3) {
            char[] arr = currency.getValute().toCharArray();
            for (char a : arr) {
                if ((int) a >= 65 && (int) a <= 90) {
                    cheek += 1;
                }
            }
        }
        return cheek == 3;
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
