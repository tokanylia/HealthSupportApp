package health.support.controller;

import health.support.dto.ProductDTO;
import health.support.entity.User;
import health.support.service.impl.ProductService;
import health.support.service.impl.UserService;
import health.support.util.сonst.Constant;
import health.support.util.сonst.Pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    final static Logger logger = LogManager.getLogger();

    @GetMapping(value = "/products")
    public String adminProducts(HttpSession session, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            int numOfProducts = productService.getNumberOfProducts(user.getId());
            final int TOTAL_PAGES;
            if (numOfProducts % Constant.PAGE_SIZE == 0) {
                TOTAL_PAGES = numOfProducts / Constant.PAGE_SIZE;
            } else {
                TOTAL_PAGES = numOfProducts / Constant.PAGE_SIZE + 1;
            }
            model.addAttribute(Constant.PARAM_PAGES, TOTAL_PAGES);
        }
        return Pages.ADMIN_MEALS_PAGE;
    }

    @PostMapping(value = "/product/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        productService.deleteProductById(id);
        logger.info("delete product with id = " + id);
        return Pages.ADMIN_MEALS_PAGE;
    }

    @PostMapping(value = "/product/edit")
    public String edit(HttpServletRequest request, @Valid ProductDTO product) {
        Boolean isPublic = request.getParameter(Constant.PARAM_PUBLIC) == null ? false : request.getParameter(Constant.PARAM_PUBLIC).equals("on") ? true : false;
        product.setCommon(isPublic);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            product.setUser(user);
            product.setDeleted(false);
            productService.updateProduct(product);
            logger.info("edit product with id = " + product.getId());
        }
        //return ADMIN_MEALS_PAGE;
        return "redirect:/admin/products";
    }
}
