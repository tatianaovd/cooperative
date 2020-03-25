package cooperative.controllers;

import cooperative.model.dto.CustomerDto;
import cooperative.service.CarService;
import cooperative.service.CustomerService;
import cooperative.service.GarageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CarService carService;

    @Autowired
    GarageService garageService;

    @ApiOperation(value = "Get all list of customers")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Ok"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found")
    })
    @GetMapping()
    public List<CustomerDto> getAllList(){
        return customerService.getAllCustomer();
    }

    @ApiOperation(value = "Find customer by id")
    @GetMapping("/findById/{id}")
    public CustomerDto getById(@PathVariable Integer id){
        return customerService.getById(id);
    }

    @ApiOperation(value = "Find customer by lastname")
    @GetMapping("/findByLastName/{lastName}")
    public List<CustomerDto> getByLastName(@PathVariable String lastName){
        return customerService.getByLastName(lastName);
    }

    @ApiOperation(value = "Add new customer")
    @PutMapping("/addCustomer")
    public String save(@RequestBody CustomerDto customerDto){
        customerService.save(customerDto);
        return "Customer was added";
    }

    @GetMapping("/findByMobilePhone/{mobilePhone}")
    public List<CustomerDto> getByMobilePhone(@PathVariable String mobilePhone){
        return customerService.getByMobilePhone(mobilePhone);
    }

    @ApiOperation(value = "Add car for customer")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Ok"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found")
    })
    @PostMapping("/addCar")
    public String addCustomerCar(Integer customerId, Integer carId) {
        customerService.addCar(customerId, carId);
        return "New customer car was added";
    }

    @ApiOperation(value = "Add garage for customer")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Ok"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found")
    })
    @PostMapping("/addGarage")
    public String addCustomerGarage(Integer customerId, Integer garageId) {
        customerService.addGarage(customerId, garageId);
        return "New customer garage was added";
    }

    @ApiOperation(value = "Edit customer")
    @PutMapping("/edit")
    public String edit(Integer customerId, @RequestBody CustomerDto customerDto){
        customerService.edit(customerId, customerDto);
        return "Customer was edited";
    }

    @ApiOperation(value = "Delete customer by id")
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return String.format("Customer with %d was removed", id);
    }
}
