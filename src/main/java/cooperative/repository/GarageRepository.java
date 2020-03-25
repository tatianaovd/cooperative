package cooperative.repository;

import cooperative.model.entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {

    List<Garage> findByGarageNumber(String carNumber);

    List<Garage> findByCustomer_lastName(String lastName);

    List<Garage> findByCustomer_MobilePhone(String mobilePhone);

    Optional<Garage> findById(Integer id);
}