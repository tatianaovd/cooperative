package cooperative.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private Set<CarDto> cars;
    private Set<GarageDto> garages;
}
