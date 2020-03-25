package cooperative.util;

import cooperative.model.dto.CarDto;
import cooperative.model.dto.CustomerDto;
import cooperative.model.dto.GarageDto;
import cooperative.model.entity.Car;
import cooperative.model.entity.Customer;
import cooperative.model.entity.Garage;

import java.util.stream.Collectors;

public class DomainMapper {

    public static CarDto toDto(Car entity) {
        return CarDto.builder()
                .id(entity.getId())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .carNumber(entity.getCarNumber())
                .customerId(entity.getCustomerId())
                .garageId(entity.getGarageId())
                .build();
    }

    public static CustomerDto toDto(Customer entity) {
        return CustomerDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .mobilePhone(entity.getMobilePhone())
                .cars(entity.getCars().stream().map(DomainMapper::toDto).collect(Collectors.toSet()))
                .garages(entity.getGarages().stream().map(DomainMapper::toDto).collect(Collectors.toSet()))
                .build();
    }

    public static GarageDto toDto(Garage entity) {
        return GarageDto.builder()
                .id(entity.getId())
                .garageNumber(entity.getGarageNumber())
                .customerId(entity.getCustomerId())
                .carId(entity.getCarId())
                .build();
    }

    public static Car toEntity(CarDto dto) {
        return Car.builder()
                .id(dto.getId())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .carNumber(dto.getCarNumber())
                .customerId(dto.getCustomerId())
                .garageId(dto.getGarageId())
                .build();
    }

    public static Customer toEntity(CustomerDto dto) {
        return Customer.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .mobilePhone(dto.getMobilePhone())
                .cars(dto.getCars().stream().map(DomainMapper::toEntity).collect(Collectors.toSet()))
                .garages(dto.getGarages().stream().map(DomainMapper::toEntity).collect(Collectors.toSet()))
                .build();
    }

    public static Garage toEntity(GarageDto dto) {
        return Garage.builder()
                .id(dto.getId())
                .garageNumber(dto.getGarageNumber())
                .customerId(dto.getCustomerId())
                .carId(dto.getCarId())
                .build();
    }
}
