package health.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import health.support.entity.User;
import health.support.entity.Water;

import java.util.Optional;

public interface WaterRepository extends JpaRepository<Water, Long> {
    Optional<Water> getWaterByUser(User user);
}
