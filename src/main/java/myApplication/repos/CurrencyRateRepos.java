package myApplication.repos;

import myApplication.domain.Rates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateRepos extends JpaRepository<Rates, Long> {

}
