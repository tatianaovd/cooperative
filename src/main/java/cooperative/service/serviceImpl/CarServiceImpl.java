package cooperative.service.serviceImpl;

import cooperative.exception.CarNotFoundException;
import cooperative.exception.CustomerNotFoundException;
import cooperative.exception.GarageNotFoundException;
import cooperative.model.dto.CarDto;
import cooperative.model.entity.Car;
import cooperative.model.entity.Customer;
import cooperative.model.entity.Garage;
import cooperative.repository.CarRepository;
import cooperative.repository.CustomerRepository;
import cooperative.repository.GarageRepository;
import cooperative.service.CarService;
import cooperative.util.DomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public CarDto getById(Integer id){
        return carRepository.findById(id)
                .map(DomainMapper::toDto)
                .orElseThrow(() -> new CarNotFoundException("Customer not found"));
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(DomainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveCar(CarDto dto) {
        Car car = DomainMapper.toEntity(dto);
        carRepository.save(car);
    }

    @Override
    public void edit(Integer carId, CarDto dto) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Customer not found"));
        Car carEntity = DomainMapper.toEntity(dto);
        Customer customer = customerRepository.findById(carEntity.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        Garage garage = garageRepository.findById(carEntity.getGarageId()).orElseThrow(() -> new GarageNotFoundException("Garage not found"));
        car.setCustomer(customer);
        car.setGarage(garage);
        car.setBrand(dto.getBrand());
        car.setCarNumber(dto.getCarNumber());
        car.setModel(dto.getModel());
        car.setCustomerId(dto.getCustomerId());
        car.setGarageId(dto.getGarageId());
        carRepository.save(car);
    }

    @Override
    public void delete(Integer id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car was not found"));
        Customer customer = car.getCustomer();
        customer.removeCar(car);
        carRepository.delete(car);
    }
}
