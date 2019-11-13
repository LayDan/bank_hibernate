package myApplication.repos;

import myApplication.domain.Currency_rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateRepos extends JpaRepository<Currency_rate, Long> {

}
