package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.OrderInvoice;

@Repository
public interface OrderInvoiceRepository extends JpaRepository<OrderInvoice, Integer> {

}
