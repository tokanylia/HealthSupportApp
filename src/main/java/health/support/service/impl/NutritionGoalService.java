package health.support.service.impl;

import health.support.repository.NutritionGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import health.support.entity.NutritionGoal;
import health.support.service.INutritionGoalService;

import java.util.List;
import java.util.Optional;

@Service
public class NutritionGoalService implements INutritionGoalService {
    @Autowired
    NutritionGoalRepository nutritionGoalRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<NutritionGoal> findNutritionGoalById(Long id) {
        return nutritionGoalRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NutritionGoal> findAllNutritionGoals() {
        return nutritionGoalRepository.findAll();
    }

    @Override
    @Transactional
    public boolean create(NutritionGoal nutritionGoal) {
        nutritionGoalRepository.save(nutritionGoal);
        return true;
    }
}
