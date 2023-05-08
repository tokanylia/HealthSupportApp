package health.support.entity;

import health.support.dto.MealsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "meals")
public class Meals implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "weight")
    private Integer weight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "eat_period_id")
    private EatPeriod eatPeriod;

    @Column(name = "date")
    private Timestamp date;

    public static Meals of(Long id, Product product, User user, Integer weight, EatPeriod eatPeriod, Timestamp date) {
        return Meals.builder()
                .id(id)
                .product(product)
                .user(user)
                .weight(weight)
                .eatPeriod(eatPeriod)
                .date(date)
                .build();
    }

    public static Meals fromDTO(MealsDTO mealsDTO) {
        return Meals.of(mealsDTO.getId(), mealsDTO.getProduct(), mealsDTO.getUser(), mealsDTO.getWeight(), mealsDTO.getEatPeriod(), mealsDTO.getDate());
    }

    public MealsDTO toDTO() {
        return MealsDTO.of(id, product, null, weight, eatPeriod, date);
    }
}
