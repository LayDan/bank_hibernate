package myApplication.service.impl;

import myApplication.domain.Bill;
import myApplication.domain.User;
import myApplication.repos.BillRepo;
import myApplication.repos.UserRepository;
import myApplication.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService implements IBillService {
    @Autowired
    private BillRepo billRepo;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addBill(Bill bill, User user) {
        billRepo.saveAndFlush(bill);
        user.getBills().add(bill);
        return userRepository.saveAndFlush(user);
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
