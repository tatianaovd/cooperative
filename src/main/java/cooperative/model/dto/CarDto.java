package cooperative.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private Integer id;
    private String brand;
    private String model;
    private String carNumber;
    private Integer customerId;
    private Integer garageId;
}
