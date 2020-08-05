package ru.innopolis.demo.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.unbescape.html.HtmlEscape;

/**
 * LoginController
 *
 * @author Stanislav_Klevtsov
 */
@Controller
public class LoginController {

    @RequestMapping("/")
    public String root(Locale locale) {
        return "redirect:/index.html";
    }

    /** Главная станица */
    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }

    /** Покупатель */
    @RequestMapping("/customer/index.html")
    public String customerIndex() {
        return "customer/index";
    }

    /** Продавец */
    @RequestMapping("/seller/index.html")
    public String sellerIndex() {
        return "seller/index";
    }

    /** Курьер */
    @RequestMapping("/courier/index.html")
    public String courierIndex() {
        return "courier/index";
    }

    /** Администратор */
    @RequestMapping("/admin/index.html")
    public String adminIndex() {
        return "admin/index";
    }

    /** Общая зона */
    @RequestMapping("/shared/index.html")
    public String sharedIndex() {
        return "shared/index";
    }

    /** Страница входа */
    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }

    /** Доступ запрещен */
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/login-success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {

        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")){
            response.sendRedirect(response.encodeRedirectURL("/admin/index.html"));
        } else if(role.contains("ROLE_CUSTOMER")) {
            response.sendRedirect(response.encodeRedirectURL("/customer/index.html"));
        } else if (role.contains("ROLE_SELLER")) {
            response.sendRedirect(response.encodeRedirectURL("/seller/index.html"));
        } else {
            response.sendRedirect(response.encodeRedirectURL("/courier/index.html"));
        }
    }

    /** Страница ошибки */
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    /** Страница ошибки */
    @RequestMapping("/403.html")
    public String forbidden() {
        return "403";
    }
}