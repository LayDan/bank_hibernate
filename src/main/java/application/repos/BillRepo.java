package application.repos;

import application.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill, Long> {
    Bill findById(long id);
}
