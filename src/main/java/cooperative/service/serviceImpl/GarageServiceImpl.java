package cooperative.service.serviceImpl;

import cooperative.exception.CarNotFoundException;
import cooperative.exception.CustomerNotFoundException;
import cooperative.exception.GarageNotFoundException;
import cooperative.model.dto.GarageDto;
import cooperative.model.entity.Car;
import cooperative.model.entity.Customer;
import cooperative.model.entity.Garage;
import cooperative.repository.CarRepository;
import cooperative.repository.CustomerRepository;
import cooperative.repository.GarageRepository;
import cooperative.service.GarageService;
import cooperative.util.DomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public GarageDto getById(Integer id){
        return garageRepository.findById(id)
                .map(DomainMapper::toDto)
                .orElseThrow(() -> new GarageNotFoundException("Garage not found"));
    }

    @Override
    public List<GarageDto> getAllGarages() {
        return garageRepository.findAll()
                .stream()
                .map(DomainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveGarage(GarageDto dto) {
        Garage garage = DomainMapper.toEntity(dto);
        garageRepository.save(garage);
    }

    @Override
    public void editGarage(Integer garageId, GarageDto dto) {
        Garage garage = garageRepository.findById(garageId).orElseThrow(() -> new GarageNotFoundException("Garage not found"));
        Garage garageEntity = DomainMapper.toEntity(dto);
        garage.setGarageNumber(garageEntity.getGarageNumber());
        Customer customer = customerRepository.findById(garageEntity.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        Car car = carRepository.findById(garageEntity.getCarId()).orElseThrow(() -> new CarNotFoundException("Car was not found"));
        garage.setCustomer(customer);
        garage.setCar(car);
        garage.setCustomerId(dto.getCustomerId());
        garage.setCarId(dto.getCarId());
        garageRepository.save(garage);
    }

    @Override
    public void deleteGarage(Integer id) {
        Garage garage = garageRepository.findById(id).orElseThrow(() -> new GarageNotFoundException("Garage not found"));
        Customer customer = garage.getCustomer();
        customer.removeGarage(garage);
        garageRepository.delete(garage);
    }
}
