package cooperative.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GarageDto {

    private int id;
    private Integer garageNumber;
    private Integer customerId;
    private Integer carId;
}
