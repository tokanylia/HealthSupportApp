package health.support.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import health.support.entity.Client;
import health.support.entity.User;
import health.support.entity.Water;
import health.support.service.impl.UserService;
import health.support.service.impl.WaterService;
import health.support.util.WaterCalculator;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class RestWaterController {
    @Autowired
    private WaterService waterService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/add/water")
    public String addWater(HttpSession session) {
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            user = userService.findUserByLogin(email).get();
        }

        Integer currentWater = 0;
        Integer maxWater = 0;
        Optional<Water> optionalWater = waterService.findWaterByUser(user);
        if (optionalWater.isPresent()) {
            Water water = optionalWater.get();
            water.setVolume(water.getVolume() + 250);
            waterService.addWater(water);
            currentWater = water.getVolume();
        } else {
            Water water = new Water();
            water.setUser(user);
            water.setVolume(250);
            waterService.addWater(water);
            currentWater = 250;
        }

        if (user.getClient() == null) {
            maxWater = WaterCalculator.AVR_WATER;
        } else {
            Client client = user.getClient();
            maxWater = WaterCalculator.calculateWaterMl(client.getGender(), client.getWeight());
        }

        int currPercentage = currentWater * 100 / maxWater;
        int percentageDiff = (currentWater * 100 / maxWater) - ((currentWater - 250) * 100 / maxWater);

        return "{\"curr_water\": " + currentWater + ", \"max_water\": " + maxWater +
                ", \"curr_percentage\": " + currPercentage +
                ", \"percentage_diff\": " + percentageDiff + "}";
    }
}
