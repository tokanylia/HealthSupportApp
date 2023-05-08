package health.support.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import health.support.dto.ProductDTO;
import health.support.entity.User;
import health.support.service.impl.ProductService;
import health.support.service.impl.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/get_products")
    public List<ProductDTO> getProducts(HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            return productService.findAllProductsForUser(user.getId());
        }
        return new ArrayList<>();
    }
}
