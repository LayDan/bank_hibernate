package application.service.impl;

import application.config.ValuteConfing;
import application.domain.*;
import application.repos.*;
import application.service.IBillService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class BillService implements IBillService {

    private BillRepo billRepo;

    private CurrencyRepository currencyRepository;

    private UserRepository userRepository;

    private TransactionRepos transactionRepos;

    private CurrencyRateRepos currencyRateRepos;

    @Override
    @Transactional
    public User addBill(String currency, double amoung, User user) {
        if (amoung >= 0 && user != null) {
            Currency valute = currencyRepository.findByValute(currency);
            Bill bill = Bill.builder()
                    .amoung(amoung)
                    .currency(valute)
                    .user(user)
                    .build();
            billRepo.saveAndFlush(bill);
            user.getBills().add(bill);
            //////////////////////////////////////////
            Transaction transaction = Transaction.builder()
                    .message("New bill")
                    .bill(bill)
                    .user(user)
                    .build();
            transactionRepos.saveAndFlush(transaction);
            //////////////////////////////////////////
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(long id) {
        Bill bill = billRepo.findById(id);
        billRepo.deleteById(id);
        Transaction transaction = Transaction.builder()
                .message("Delete bill")
                .bill(bill)
                .user(bill.getUser())
                .build();
        transactionRepos.saveAndFlush(transaction);
    }

    @Override
    @Transactional
    public void addMoney(long id, double amoung) {
            Bill bill = billRepo.findById(id);
        if (bill != null) {
            bill.setAmoung(amoung + bill.getAmoung());
            billRepo.save(bill);

            Transaction transaction = Transaction.builder()
                    .message("AddMoney")
                    .bill(bill)
                    .user(bill.getUser())
                    .build();
            transactionRepos.saveAndFlush(transaction);
        }
    }

    @Override
    @Transactional
    public void transfer(long numberCard, double amoung, long selfNumberCard, User user) {
        Bill self = billRepo.findById(selfNumberCard);
        Bill bill = billRepo.findById(numberCard);
        if ((self != null && bill != null) &&
                (amoung > 0 && self.getAmoung() >= amoung) &&
                (convertMoney(self, bill.getCurrency(), amoung) != 0 || bill.getCurrency() == self.getCurrency())) {

            self.setAmoung(self.getAmoung() - amoung);

            addMoney(bill.getNumberCard(), convertMoney(self, bill.getCurrency(), amoung));

            billRepo.save(bill);
            userRepository.save(bill.getUser());

            billRepo.save(self);
            userRepository.save(self.getUser());

            //////////////////////////////////////////  Transaction

            Transaction transaction = Transaction.builder()
                    .message("transfer to card")
                    .bill(self)
                    .user(user)
                    .build();
            transactionRepos.saveAndFlush(transaction);

            Transaction transaction1 = Transaction.builder()
                    .message("add Money")
                    .bill(bill)
                    .user(user)
                    .build();
            transactionRepos.saveAndFlush(transaction1);
        }
    }

    @Override
    @Transactional
    public void convertToNewCurrency(long numberCard, String currency) {
        Bill bill = billRepo.findById(numberCard);
        double multicast = 0;
        Currency newCurrency = currencyRepository.findByValute(currency);
        Rates rates = null;
        if (bill != null) {
            List<Rates> all = currencyRateRepos.findAll();
            for (Rates a : all) {
                if (a.getFirst().getValute().equals(bill.getCurrency().getValute()) && a.getSecond().getValute().equals(currency)) {
                    rates = a;
                    multicast = rates.getCoefficient();
                    continue;
                }
                if (a.getFirst().getValute().equals(currency) && a.getSecond().getValute().equals(bill.getCurrency().getValute())) {
                    rates = a;
                    multicast = 1 / rates.getCoefficient();
                }
            }

            if (rates != null && multicast != 0) {

                bill.setAmoung(bill.getAmoung() * multicast);
                bill.setCurrency(newCurrency);
                billRepo.save(bill);

                //////////////////////////////////////////  Transaction

                Transaction transaction = Transaction.builder()
                        .message("edit currency")
                        .bill(bill)
                        .user(bill.getUser())
                        .build();
                transactionRepos.saveAndFlush(transaction);
            }
        }
    }

    @Override
    @Transactional
    public double convertMoney(Bill bill, Currency currency, double amoung) {
        double multicast = 0;
        Rates rates = null;
        List<Rates> all = currencyRateRepos.findAll();
        if (bill.getCurrency() != currency) {
            for (Rates a : all) {
                if (a.getFirst().getValute().equals(bill.getCurrency().getValute()) && a.getSecond().getValute().equals(currency.getValute())) {
                    rates = a;
                    multicast = rates.getCoefficient();
                    continue;
                }
                if (a.getFirst().getValute().equals(currency.getValute()) && a.getSecond().getValute().equals(bill.getCurrency().getValute())) {
                    rates = a;
                    multicast = 1 / rates.getCoefficient();
                }
            }
        } else {
            return amoung;
        }
        if (rates != null && multicast != 0) {

            amoung = amoung * multicast;

            return Math.round(amoung);
        } else {
            return 0;
        }
    }

    @Override
    @Transactional
    public double convertAllMoneyToUAH(User user) {
        user = userRepository.findByUsername(user.getUsername());
        Currency currency = currencyRepository.findByValute(new ValuteConfing().getGlobalValute());
        if (user != null && currency != null && !user.getBills().isEmpty()) {
            double amoung = 0;
            for (Bill b : user.getBills()) {
                if (b.getCurrency().getValute().equals(currency.getValute())) {
                    amoung = amoung + b.getAmoung();
                } else {
                    amoung = amoung + convertMoney(b, currency, b.getAmoung());
                }
            }
            return amoung;
        }
        return 0;
    }
}
