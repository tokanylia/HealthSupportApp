package health.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import health.support.entity.EatPeriod;

public interface EatPeriodRepository extends JpaRepository<EatPeriod, Long> {
}
