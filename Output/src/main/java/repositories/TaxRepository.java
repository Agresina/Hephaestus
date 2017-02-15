package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {

}
