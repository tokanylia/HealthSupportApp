package health.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import health.support.entity.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}
