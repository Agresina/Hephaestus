package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.${class.name};

@Repository
public interface ${class.name}Repository extends JpaRepository<${class.name}, Integer> {

}
