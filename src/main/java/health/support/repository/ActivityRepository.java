package health.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import health.support.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
