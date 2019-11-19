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
    public void addCurrency(Currency currency) {
        if (currency.getValute().length() == 3) {
            boolean yes = false;
            boolean cheek = false;
            char[] arr = currency.getValute().toCharArray();
            for (char a : arr) {
                if ((int) a < 65 || (int) a > 90) {
                    cheek = true;
                }

            }
            if (!cheek) {
                if (currencyRepository.findAll() == null) {
                    currencyRepository.saveAndFlush(currency);
                } else {
                    for (Currency currency1 : currencyRepository.findAll()) {
                        if (currency1.getValute().equals(currency.getValute())) {
                            yes = true;
                        }
                    }
                }
                if (!yes) {
                    currencyRepository.saveAndFlush(currency);
                }
            }
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
