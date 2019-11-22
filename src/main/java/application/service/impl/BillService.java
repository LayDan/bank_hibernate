package application.service.impl;

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
                    .userId(user)
                    .build();
            billRepo.saveAndFlush(bill);
            user.getBills().add(bill);
            //////////////////////////////////////////
            Transaction transaction = Transaction.builder()
                    .message("New bill")
                    .billId(bill.getNumberCard())
                    .userId(user.getId())
                    .build();
            transactionRepos.saveAndFlush(transaction);
            //////////////////////////////////////////
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        Bill bill = billRepo.findById(id);
        billRepo.deleteById(id);
        Transaction transaction = Transaction.builder()
                .message("Delete bill")
                .billId(id)
                .userId(bill.getUserId().getId())
                .build();
        transactionRepos.saveAndFlush(transaction);
    }

    @Override
    public Bill edit(Bill bill) {
        return billRepo.save(bill);
    }

    @Override
    public Bill findByid(long id) {
        return billRepo.findById(id);
    }

    @Override

    public void addMoney(long id, double amoung) {
        if (billRepo.findById(id) != null) {
            Bill bill = billRepo.findById(id);
            bill.setAmoung(amoung + bill.getAmoung());
            billRepo.save(bill);

            Transaction transaction = Transaction.builder()
                    .message("AddMoney")
                    .billId(id)
                    .userId(bill.getUserId().getId())
                    .build();
            transactionRepos.saveAndFlush(transaction);
        }
    }

    @Override
    public void transfer(long numberCard, double amoung, long selfNumberCard, User user) {
        Bill self = billRepo.findById(selfNumberCard);
        Bill bill = billRepo.findById(numberCard);
        if ((self != null && bill != null) &&
                (amoung > 0 && self.getAmoung() >= amoung) &&
                (convertMoney(self, bill.getCurrency(), amoung) != 0 || bill.getCurrency() == self.getCurrency())) {

            self.setAmoung(self.getAmoung() - amoung);

            addMoney(bill.getNumberCard(), convertMoney(self, bill.getCurrency(), amoung));

            billRepo.save(bill);
            userRepository.save(bill.getUserId());

            billRepo.save(self);
            userRepository.save(self.getUserId());

            //////////////////////////////////////////  Transaction

            Transaction transaction = Transaction.builder()
                    .message("transfer to card")
                    .billId(self.getNumberCard())
                    .userId(user.getId())
                    .build();
            transactionRepos.saveAndFlush(transaction);

            Transaction transaction1 = Transaction.builder()
                    .message("add Money")
                    .billId(bill.getNumberCard())
                    .userId(user.getId())
                    .build();
            transactionRepos.saveAndFlush(transaction1);
        }
    }

    @Override
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
                    multicast = rates.getX();
                    continue;
                }
                if (a.getFirst().getValute().equals(currency) && a.getSecond().getValute().equals(bill.getCurrency().getValute())) {
                    rates = a;
                    multicast = 1 / rates.getX();
                }
            }

            if (rates != null && multicast != 0) {

                bill.setAmoung(bill.getAmoung() * multicast);
                bill.setCurrency(newCurrency);
                billRepo.save(bill);

                //////////////////////////////////////////  Transaction

                Transaction transaction = Transaction.builder()
                        .message("edit currency")
                        .billId(bill.getUserId().getId())
                        .userId(bill.getNumberCard())
                        .build();
                transactionRepos.saveAndFlush(transaction);
            }
        }
    }

    @Override
    public double convertMoney(Bill bill, Currency currency, double amoung) {
        double multicast = 0;
        Rates rates = null;
        List<Rates> all = currencyRateRepos.findAll();
        if (bill.getCurrency() != currency) {
            for (Rates a : all) {
                if (a.getFirst().getValute().equals(bill.getCurrency().getValute()) && a.getSecond().getValute().equals(currency.getValute())) {
                    rates = a;
                    multicast = rates.getX();
                    continue;
                }
                if (a.getFirst().getValute().equals(currency.getValute()) && a.getSecond().getValute().equals(bill.getCurrency().getValute())) {
                    rates = a;
                    multicast = 1 / rates.getX();
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
    public double convertAllMoneyToUAH(User user) {
        user = userRepository.findByUsername(user.getUsername());
        double amoung = 0;
        Currency currency = currencyRepository.findByValute("UAH");
        if (currency != null && user != null && !user.getBills().isEmpty()) {
            Iterable<Bill> bills = user.getBills();
            for (Bill b : bills) {
                if (b.getCurrency().getValute().equals("UAH")) {
                    amoung += b.getAmoung();
                } else {
                    amoung += convertMoney(b, currency, b.getAmoung());
                }
            }
            return amoung;
        }
        return 0;
    }
}
