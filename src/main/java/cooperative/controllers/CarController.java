package cooperative.controllers;

import cooperative.model.dto.CarDto;
import cooperative.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @ApiOperation(value = "Get all list of cars")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Ok"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found")
    })
    @GetMapping()
    public List<CarDto> getAllList(){
        return carService.getAllCars();
    }

    @ApiOperation(value = "Add new car")
    @PostMapping("/addCar")
    public String save(@RequestBody CarDto carDto){
        carService.saveCar(carDto);
        return "Car was added";
    }

    @ApiOperation(value = "Find car by id")
    @GetMapping("/findById/{id}")
    public CarDto getById(@PathVariable Integer id){
        return carService.getById(id);
    }

    @ApiOperation(value = "Edit car")
    @PutMapping("/edit")
    public String edit(Integer carId, @RequestBody CarDto carDto){
        carService.edit(carId, carDto);
        return "Customer was edited";
    }

    @ApiOperation(value = "Delete car by id")
    @DeleteMapping("/deleteCar/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        carService.delete(id);
        return String.format("Car with %d was removed", id);
    }
}
