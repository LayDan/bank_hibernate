package myApplication.repos;

import myApplication.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill, Long> {
    Bill findById(long id);
}
