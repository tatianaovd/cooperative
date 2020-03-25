package cooperative.repository;

import cooperative.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByCarNumber(String carNumber);

    List<Car> findByCustomer_LastName(String lastName);

    List<Car> findByCustomer_MobilePhone(String mobilePhone);

    Optional<Car> findById(Integer id);
}
