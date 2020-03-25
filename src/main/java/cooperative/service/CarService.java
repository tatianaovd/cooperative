package cooperative.service;

import cooperative.model.dto.CarDto;

import java.util.List;

public interface CarService {

    CarDto getById(Integer id);

    List<CarDto> getAllCars();

    void saveCar(CarDto dto);

    void edit(Integer carId, CarDto dto);

    void delete(Integer id);
}
