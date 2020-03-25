package cooperative.model.entity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull(message = "First name is required")
    private String firstName;

    @NotNull(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Mobile phone is required")
    private String mobilePhone;

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Car> cars = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Garage> garages = new HashSet<>();

    public void addCar(Car car) {
        cars.add(car);
        car.setCustomer(this);
    }

    public void addCars(Set<Car> cars) {
        this.cars.addAll(cars);
        cars.forEach(car -> car.setCustomer(this));
    }

    public void addGarage(Garage garage) {
        garages.add(garage);
        garage.setCustomer(this);
    }

    public void addGarages(Set<Garage> garages) {
        this.garages.addAll(garages);
        garages.forEach(garage -> garage.setCustomer(this));
    }

    public void removeCar(Car car) {
        cars.remove(car);
        car.setCustomer(null);
    }

    public void removeGarage(Garage garage) {
        garages.remove(garage);
        garage.setCustomer(null);
    }
}
