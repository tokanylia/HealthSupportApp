package health.support.service;

import org.springframework.transaction.annotation.Transactional;
import health.support.dto.UserDTO;
import health.support.entity.User;

import java.util.Optional;

public interface IUserService {
    @Transactional
    boolean createUser(UserDTO user);

    boolean updateUser(User user);

    @Transactional(readOnly = true)
    Optional<User> findUserByLogin(String login);

    Integer countCalories(User user);

    Integer countProtein(User user);

    Integer countFats(User user);

    Integer countCarbohydrates(User user);
}
