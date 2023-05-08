package health.support.service;

import health.support.entity.NutritionGoal;

import java.util.List;
import java.util.Optional;

public interface INutritionGoalService {
    Optional<NutritionGoal> findNutritionGoalById(Long id);

    List<NutritionGoal> findAllNutritionGoals();

    boolean create(NutritionGoal nutritionGoal);
}
