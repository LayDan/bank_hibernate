package application.service.impl;

import application.domain.Currency;
import application.domain.Rates;
import application.repos.CurrencyRateRepos;
import application.repos.CurrencyRepository;
import application.service.ICurrencyRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class CurrencyRateService implements ICurrencyRateService {

    private CurrencyRateRepos currencyRateRepos;

    private CurrencyRepository currencyRepository;

    @Override
    @Transactional
    public Rates add(String first, String second, double coefficient) {

        ArrayList<Rates> ratesArrayList = (ArrayList<Rates>) currencyRateRepos.findAll();
        if (!ratesArrayList.isEmpty() && !first.equals(second) && coefficient > 0) {
            boolean equals = ratesArrayList.stream()
                    .anyMatch(rates -> (rates.getFirst().getValute().equals(first) && rates.getSecond().getValute().equals(second))
                            || (rates.getFirst().getValute().equals(second) && rates.getSecond().getValute().equals(first)));

            if (!equals) {
                Currency currencyFirst = currencyRepository.findByValute(first);
                Currency currencySecond = currencyRepository.findByValute(second);
                Rates rates = Rates.builder()
                        .first(currencyFirst)
                        .second(currencySecond)
                        .coefficient(coefficient)
                        .build();
                currencyRateRepos.saveAndFlush(rates);
                return rates;
            }
        }
        return null;
    }

    @Override
    public Iterable<Rates> findAll() {
        return currencyRateRepos.findAll();
    }
}
