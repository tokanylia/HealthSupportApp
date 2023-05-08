package health.support.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import health.support.dto.ProductDTO;
import health.support.entity.User;
import health.support.service.impl.ProductService;
import health.support.service.impl.UserService;
import health.support.util.сonst.Constant;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestAdminController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/get_admin_products")
    public List<ProductDTO> getAdminProducts(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                             @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();

            if (!name.isEmpty()) {
                return productService.findAllProductsByNameForUser(user.getId(), name);
            }

            return productService.getProductsByPageable(user.getId(),
                    PageRequest.of(page - 1, Constant.PAGE_SIZE));
        }
        return new ArrayList<>();
    }
}
