package myApplication.service.impl;

import myApplication.domain.Bill;
import myApplication.repos.BillRepo;
import myApplication.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService implements IBillService {
    @Autowired
    private BillRepo billRepo;


    @Override
    public Bill addBill(Bill bill) {
        bill.setAmoung(0);
        Bill newBill = billRepo.saveAndFlush(bill);
        return newBill;
    }

    @Override
    public void delete(long id) {
        billRepo.deleteById(id);
    }

    @Override
    public Bill edit(Bill bill) {
        return billRepo.saveAndFlush(bill);
    }

    @Override
    public Bill findByid(long id) {
        return billRepo.findById(id);
    }
}
