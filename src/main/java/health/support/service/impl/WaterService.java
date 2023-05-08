package health.support.service.impl;

import health.support.repository.WaterRepository;
import health.support.service.IWaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import health.support.entity.User;
import health.support.entity.Water;

import java.util.Optional;

@Service
public class WaterService implements IWaterService {
    @Autowired
    WaterRepository waterRepository;

    @Override
    @Transactional
    public Optional<Water> findWaterByUser(User user) {
        return waterRepository.getWaterByUser(user);
    }

    @Override
    @Transactional
    public boolean addWater(Water water) {
        waterRepository.save(water);
        return true;
    }
}
