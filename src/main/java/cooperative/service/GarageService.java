package cooperative.service;

import cooperative.model.dto.GarageDto;

import java.util.List;

public interface GarageService {
    GarageDto getById(Integer id);

    List<GarageDto> getAllGarages();

    void saveGarage(GarageDto dto);

    void editGarage(Integer carId, GarageDto dto);

    void deleteGarage(Integer id);
}
