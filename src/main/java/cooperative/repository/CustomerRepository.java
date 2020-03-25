package cooperative.repository;

import cooperative.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByMobilePhone(String mobilePhone);

    Optional<Customer> findById(Integer id);
}