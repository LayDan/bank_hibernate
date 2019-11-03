package myApplication.repos;

import myApplication.domain.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepo extends CrudRepository<Bill, Long> {
}
