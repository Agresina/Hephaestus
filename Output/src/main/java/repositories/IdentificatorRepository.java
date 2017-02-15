package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Identificator;

@Repository
public interface IdentificatorRepository extends JpaRepository<Identificator, Integer> {

}
