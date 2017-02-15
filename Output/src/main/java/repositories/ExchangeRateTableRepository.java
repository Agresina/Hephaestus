package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.ExchangeRateTable;

@Repository
public interface ExchangeRateTableRepository extends JpaRepository<ExchangeRateTable, Integer> {

}
