package health.support.service;

import health.support.entity.EatPeriod;

import java.util.List;
import java.util.Optional;

public interface IEatPeriodService {
    Optional<EatPeriod> findEatPeriodById(Long id);

    List<EatPeriod> findAllEatPeriods();

    boolean create(EatPeriod eatPeriod);
}
