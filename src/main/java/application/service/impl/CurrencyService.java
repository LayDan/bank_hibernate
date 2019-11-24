package application.service.impl;

import application.domain.Currency;
import application.repos.CurrencyRepository;
import application.service.ICurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class CurrencyService implements ICurrencyService {

    private CurrencyRepository currencyRepository;

    @Override
    @Transactional
    public Currency addCurrency(Currency currency) {
        if (currency != null) {
            ArrayList<Currency> currencyIterable = (ArrayList<Currency>) currencyRepository.findAll();
            if (!currencyIterable.isEmpty()) {
                boolean equals = currencyIterable.stream()
                        .anyMatch(currency1 -> (currency1.getValute().equals(currency.getValute())));

                boolean cheek = cheek(currency);
                if (cheek && !equals) {
                    Currency newCurrency = Currency.builder()
                            .valute(currency.getValute())
                            .build();
                    currencyRepository.saveAndFlush(newCurrency);
                    return newCurrency;
                }
            }
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
    @Transactional
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
