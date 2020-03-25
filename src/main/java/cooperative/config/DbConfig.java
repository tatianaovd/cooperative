package cooperative.config;

import cooperative.model.entity.Car;
import cooperative.model.entity.Customer;
import cooperative.repository.CarRepository;
import cooperative.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbConfig {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;

    @PostConstruct
    public void init() {

        carRepository.save(Car.builder()
                .carNumber("BB5432HH")
                .brand("Toyota")
                .model("Camry")
                .build());
        carRepository.save(Car.builder()
                .carNumber("FF2345BN")
                .brand("Audi")
                .model("Audi TT")
                .build());
        carRepository.save(Car.builder()
                .carNumber("FA2345BN")
                .brand("Audi")
                .model("Audi A8")
                .build());
        carRepository.save(Car.builder()
                .carNumber("AA2345BN")
                .brand("Audi")
                .model("Audi Q3")
                .build());

        customerRepository.save(Customer.builder()
                .firstName("Riordan")
                .lastName("Redman")
                .mobilePhone("0634563241")
                .build());
        customerRepository.save(Customer.builder()
                .firstName("Amalita")
                .lastName("Lyngley")
                .mobilePhone("0634563242")
                .build());
        customerRepository.save(Customer.builder()
                .firstName("Pattie")
                .lastName("Guerrero")
                .mobilePhone("0634563243")
                .build());
        customerRepository.save(Customer.builder()
                .firstName("Lorene")
                .lastName("Pellegrini")
                .mobilePhone("0634563244")
                .build());
    }
}
