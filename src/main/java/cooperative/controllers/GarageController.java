package cooperative.controllers;

import cooperative.model.dto.GarageDto;
import cooperative.service.GarageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.List;

@RestController
@RequestMapping("/garages")
public class GarageController {

    @Autowired
    GarageService garageService;

    @ApiOperation(value = "Get all list of garages")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Ok"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found")
    })
    @GetMapping()
    public List<GarageDto> getAllList() {
        return garageService.getAllGarages();
    }

    @ApiOperation(value = "Find garage by id")
    @GetMapping("/findById/{id}")
    public GarageDto getById(@PathVariable int id){
        return garageService.getById(id);
    }

    @ApiOperation(value = "Add new garage")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Ok"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found")
    })
    @PostMapping("/add")
    public String addGarage(@RequestBody GarageDto garageDto) {
        garageService.saveGarage(garageDto);
        return "New garage was added";
    }

    @ApiOperation(value = "Edit garage")
    @PutMapping("/edit")
    public String edit(Integer garageId, @RequestBody GarageDto carDto){
        garageService.editGarage(garageId, carDto);
        return "Garage was edited";
    }

    @ApiOperation(value = "Delete garage")
    @DeleteMapping("/deleteGarage/{id}")
    public String deleteGarage(@PathVariable Integer id) {
        garageService.deleteGarage(id);
        return String.format("Garage with %d was removed", id);
    }
}
