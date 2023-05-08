package health.support.service;

import health.support.dto.UserDTO;
import health.support.repository.UserRepository;
import health.support.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        when(this.userRepository.existsByLogin(any())).thenReturn(true);

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("yulia.tokan.11@gmail.com");

        boolean res = userService.createUser(userDTO);
        assertFalse(res);
    }
}
