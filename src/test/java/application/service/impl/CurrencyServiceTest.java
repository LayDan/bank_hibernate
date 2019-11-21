package application.service.impl;

import application.domain.Currency;
import application.repos.CurrencyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CurrencyServiceTest {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void Testcheek() {
        Currency currency = new Currency();
        currency.setId(4);
        currency.setValute("AAA");
        CurrencyService currencyService = new CurrencyService(currencyRepository);
        Assert.assertTrue(currencyService.cheek(currency));
//        Assert.assertFalse(currencyService.cheek(currency));
    }
}