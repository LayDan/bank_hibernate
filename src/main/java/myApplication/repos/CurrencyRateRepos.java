package myApplication.repos;

import myApplication.domain.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateRepos extends JpaRepository<CurrencyRate, Long> {

}
