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
@ToString(exclude = {"customer", "car"})
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull(message = "Garage number is required")
    private Integer garageNumber;

    @Column(name = "customer_id", updatable = false, insertable = false)
    private Integer customerId;

    @Column(name = "car_id", updatable = false, insertable = false)
    private Integer carId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "garage")
    @JoinColumn(name = "car_id")
    private Car car;
}
