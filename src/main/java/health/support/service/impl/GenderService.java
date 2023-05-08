package health.support.service.impl;

import health.support.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import health.support.entity.Gender;
import health.support.service.IGenderService;

import java.util.List;
import java.util.Optional;

@Service
public class GenderService implements IGenderService {
    @Autowired
    GenderRepository genderRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Gender> findGenderById(Long id) {
        return genderRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gender> findAllGenders() {
        return genderRepository.findAll();
    }

    @Override
    @Transactional
    public boolean create(Gender gender) {
        genderRepository.save(gender);
        return true;
    }
}
