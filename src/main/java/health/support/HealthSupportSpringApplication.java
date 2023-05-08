package health.support;

import health.support.entity.*;
import health.support.repository.UserRepository;
import health.support.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.external.spring.entity.*;
import health.support.filter.XSSFilter;
import ua.external.spring.service.impl.*;

@SpringBootApplication
public class HealthSupportSpringApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(HealthSupportSpringApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean xssPreventFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new XSSFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Bean
    public CommandLineRunner addGender(final GenderService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                service.create(new Gender("Female"));
                service.create(new Gender("Male"));
            }
        };
    }

    @Bean
    public CommandLineRunner addRole(final UserRoleService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                service.create(new UserRole("USER"));
                service.create(new UserRole("ADMIN"));
            }
        };
    }

    @Bean
    public CommandLineRunner addActivity(final ActivityService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                service.create(new Activity("High", 1.9));
                service.create(new Activity("Medium", 1.5));
                service.create(new Activity("Low", 1.2));
            }
        };
    }

    @Bean
    public CommandLineRunner addNutrGoal(final NutritionGoalService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                service.create(new NutritionGoal("Weight Lost", 0.9));
                service.create(new NutritionGoal("Weight Maintenance", 1.0));
                service.create(new NutritionGoal("Weight Gain", 1.1));
            }
        };
    }

    @Bean
    public CommandLineRunner addEatPeriod(final EatPeriodService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                service.create(new EatPeriod("Breakfast"));
                service.create(new EatPeriod("Lunch"));
                service.create(new EatPeriod("Snack"));
                service.create(new EatPeriod("Supper"));
            }
        };
    }

    @Bean
    public CommandLineRunner addAdmin(final UserService userService, final UserRoleService userRoleService, final UserRepository userRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                User user = User.builder()
                        .login("test.user@gmail.com")
                        .role(userRoleService.findUserRoleByName("ADMIN").get())
                        .password(passwordEncoder.encode("Qwe12345"))
                        .build();
                userRepository.save(user);
            }
        };
    }

}
