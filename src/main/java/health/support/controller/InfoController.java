package health.support.controller;

import health.support.util.сonst.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class InfoController {

    @RequestMapping(value = "/")
    public String index(HttpSession session, Model model, HttpServletRequest request) {
        return Pages.INDEX_PAGE;
    }

    @GetMapping(value = "/info/about")
    public String about(HttpSession session, Model model) {
        return Pages.ABOUT_PAGE;
    }

    @GetMapping(value = "/info/recipes")
    public String recipes(HttpSession session, Model model) {
        return Pages.RECIPES_PAGE;
    }

    @GetMapping(value = "/info/contact")
    public String contact(HttpSession session, Model model) {
        return Pages.CONTACT_PAGE;
    }

    @GetMapping(value = "/info/recipes/{id}")
    public String recipes(@PathVariable Long id) {
        return Pages.RECIPES_INFO_PAGE;
    }
}
