package health.support.service.impl;

import health.support.repository.EatPeriodRepository;
import health.support.service.IEatPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import health.support.entity.EatPeriod;

import java.util.List;
import java.util.Optional;

@Service
public class EatPeriodService implements IEatPeriodService {
    @Autowired
    EatPeriodRepository eatPeriodRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<EatPeriod> findEatPeriodById(Long id) {
        return eatPeriodRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EatPeriod> findAllEatPeriods() {
        return eatPeriodRepository.findAll();
    }

    @Override
    @Transactional
    public boolean create(EatPeriod eatPeriod) {
        eatPeriodRepository.save(eatPeriod);
        return true;
    }
}
