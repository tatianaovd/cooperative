package cooperative.service;

import cooperative.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getById(Integer id);

    List<CustomerDto> getByLastName(String lastName);

    List<CustomerDto> getByMobilePhone(String mobilePhone);

    List<CustomerDto> getAllCustomer();

    void addCar(Integer customerId, Integer carId);

    void addGarage(Integer customerId, Integer garageId);

    void edit(Integer customerId, CustomerDto dto);

    void save(CustomerDto dto);

    void delete(Integer id);
}
