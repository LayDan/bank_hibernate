package application.repos;

import application.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepos extends JpaRepository<Transaction, Long> {
}
