package health.support.service;


import org.springframework.transaction.annotation.Transactional;
import health.support.entity.User;
import health.support.entity.Water;

import java.util.Optional;

public interface IWaterService {
    Optional<Water> findWaterByUser(User user);

    @Transactional
    boolean addWater(Water water);
}
