package health.support.service;

import health.support.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface IUserRoleService {
    Optional<UserRole> findUserRoleById(Long id);

    List<UserRole> findAllUserRoles();

    Optional<UserRole> findUserRoleByName(String role);

    boolean create(UserRole role);
}
