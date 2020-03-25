package cooperative.service.serviceImpl;

import cooperative.exception.CarNotFoundException;
import cooperative.exception.CustomerNotFoundException;
import cooperative.exception.GarageNotFoundException;
import cooperative.model.dto.CustomerDto;
import cooperative.model.entity.Car;
import cooperative.model.entity.Customer;
import cooperative.model.entity.Garage;
import cooperative.repository.CarRepository;
import cooperative.repository.CustomerRepository;
import cooperative.repository.GarageRepository;
import cooperative.service.CustomerService;
import cooperative.util.DomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GarageRepository garageRepository;

    @Override
    public CustomerDto getById(Integer id){
        return customerRepository.findById(id)
                .map(DomainMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    @Override
    public List<CustomerDto> getByLastName(String lastName) {
        return customerRepository.findByLastName(lastName)
                .stream()
                .map(DomainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getByMobilePhone(String mobilePhone) {
        return customerRepository.findByMobilePhone(mobilePhone)
                .stream()
                .map(DomainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(DomainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addCar(Integer customerId, Integer carId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car was not found"));
        customer.addCar(car);
        customerRepository.save(customer);
    }

    @Override
    public void addGarage(Integer customerId, Integer garageId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        Garage garage = garageRepository.findById(garageId).orElseThrow(() -> new GarageNotFoundException("Garage was not found"));
        customer.addGarage(garage);
        customerRepository.save(customer);
    }

    @Override
    public void edit(Integer customerId, CustomerDto dto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        Customer customerEntity = DomainMapper.toEntity(dto);
        Set<Car> cars = customerEntity.getCars();
        Set<Garage> garages = customerEntity.getGarages();
        customer.setFirstName(customerEntity.getFirstName());
        customer.setLastName(customerEntity.getLastName());
        customer.setMobilePhone(customerEntity.getMobilePhone());
        customer.addCars(cars);
        customer.addGarages(garages);
        customerRepository.save(customer);
    }

    @Override
    public void save(CustomerDto dto) {
        Customer customer = DomainMapper.toEntity(dto);
        Set<Car> cars = customer.getCars();
        customer.addCars(cars);
        Set<Garage> garages = customer.getGarages();
        customer.addGarages(garages);
        customerRepository.save(customer);
    }

    @Override
    public void delete(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }
}
