package application.service.impl;

import application.domain.Currency;
import application.repos.CurrencyRepository;
import application.service.ICurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CurrencyService implements ICurrencyService {

    private CurrencyRepository currencyRepository;

    @Override
    public Currency addCurrency(Currency currency) {
        Currency newCurrency = Currency.builder()
                .valute(currency.getValute())
                .build();
        boolean yes = true;
        boolean cheek = cheek(newCurrency);
        if (cheek && currencyRepository.findAll().isEmpty()) {
            currencyRepository.saveAndFlush(newCurrency);
            return newCurrency;
        } else if (cheek && !currencyRepository.findAll().isEmpty()) {
            for (Currency currency1 : currencyRepository.findAll()) {
                if (currency1.getValute().equals(newCurrency.getValute())) {
                    yes = false;
                    break;
                }
            }
        }
        if (cheek && yes) {
            currencyRepository.saveAndFlush(newCurrency);
            return newCurrency;
        }
        return null;
    }

    boolean cheek(Currency currency) {
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
