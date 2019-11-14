package myApplication.service.impl;

import myApplication.domain.*;
import myApplication.repos.*;
import myApplication.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BillService implements IBillService {
    @Autowired
    private BillRepo billRepo;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepos transactionRepos;

    @Autowired
    private CurrencyRateRepos currencyRateRepos;

    @Override
    public User addBill(String currency, double amoung, User user) {
        if (amoung >= 0 && user != null) {
            Currency valute = currencyRepository.findByValute(currency);
            Bill bill = new Bill(valute, amoung, user);
            billRepo.saveAndFlush(bill);
            user.getBills().add(bill);
            //////////////////////////////////////////
            Transaction transaction = new Transaction();
            transaction.setUser_id(user.getId());
            transaction.setBill_id(bill.getNumber_card());
            transaction.setMessage("New bill");
            transactionRepos.saveAndFlush(transaction);
            ///////////////////////////////////////////
        }
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(long id) {
        billRepo.deleteById(id);
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
    public User addMoney(long id, double amoung) {
        if (billRepo.findById(id) != null) {
            User user = userRepository.findByUsername(billRepo.findById(id).getUser_id().getUsername());
            Set<Bill> bills = user.getBills();

            for (Bill b : bills) {
                if (b.getNumber_card() == id) {
                    b.setAmoung(amoung + b.getAmoung());
                    billRepo.save(b);
                }
            }
            billRepo.saveAndFlush(billRepo.findById(id));
            //////////////////////////////////////////
            Transaction transaction = new Transaction();
            transaction.setUser_id(user.getId());
            transaction.setBill_id(id);
            transaction.setMessage("AddMoney");
            transactionRepos.saveAndFlush(transaction);
            ///////////////////////////////////////////

            return userRepository.saveAndFlush(user);
        }
        return null;
    }

    @Override
    public void transfer(long number_card, double amoung, long self_number_card, User user) {
        Bill self = billRepo.findById(self_number_card);
        Bill bill = billRepo.findById(number_card);
        if (self != null && bill != null) {
            if (amoung > 0 && self.getAmoung() >= amoung) {
                if (convertMoney(self, bill.getCurrency(), amoung) != 0) {

                    self.setAmoung(self.getAmoung() - amoung);

                    addMoney(bill.getNumber_card(), convertMoney(self, bill.getCurrency(), amoung));

                    billRepo.save(bill);
                    userRepository.save(bill.getUser_id());

                    billRepo.save(self);
                    userRepository.save(self.getUser_id());
                }
            }
        }
    }

    @Override
    public void convert_to_newCurrency(long number_card, String currency) {
        Bill bill = billRepo.findById(number_card);
//        Currency newCurrency = currencyRepository.findByValute(currency);
        Rates rates = null;
        if (bill != null) {
            List<Rates> all = currencyRateRepos.findAll();
            for (Rates a : all) {
                if (a.getFirst().getValue().equals(bill.getCurrency().getValue()) && a.getSecond().getValue().equals(currency)) {
                    rates = a;
                    break;
                }
            }

            if (rates != null) {
                double multicast = rates.getX();
                bill.setAmoung(bill.getAmoung() * multicast);
                bill.setCurrency(rates.getSecond());
                billRepo.save(bill);
            }
        }
    }

    @Override
    public double convertMoney(Bill bill, Currency currency, double amoung) {
        double multicast = 0;
        Rates rates = null;
        List<Rates> all = currencyRateRepos.findAll();
        for (Rates a : all) {
            if (a.getFirst().getValue().equals(bill.getCurrency().getValue()) && a.getSecond().getValue().equals(currency.getValue())) {
                rates = a;
                multicast = rates.getX();
                break;
            }
            if (a.getFirst().getValue().equals(currency.getValue()) && a.getSecond().getValue().equals(bill.getCurrency().getValue())) {
                rates = a;
                multicast = 1 / rates.getX();
                break;
            }
        }
        if (rates != null && multicast != 0) {

            amoung = amoung * multicast;

            return Math.round(amoung);
        } else {
            return 0;
        }
    }
}
