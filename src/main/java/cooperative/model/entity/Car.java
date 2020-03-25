package cooperative.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "customer")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Сar number is required")
    private String carNumber;
    private String brand;
    private String model;

    @Column(name = "customer_id", updatable = false, insertable = false)
    private Integer customerId;
    @Column(name = "garage_id", updatable = false, insertable = false)
    private Integer garageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;
}
