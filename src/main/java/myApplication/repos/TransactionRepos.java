package myApplication.repos;

import myApplication.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepos extends JpaRepository<Transaction, Long> {
}
