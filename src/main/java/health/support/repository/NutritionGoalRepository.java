package health.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import health.support.entity.NutritionGoal;

public interface NutritionGoalRepository extends JpaRepository<NutritionGoal, Long> {
}
