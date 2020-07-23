package ru.innopolis.demo.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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

    /** Пользовательская зона */
    @RequestMapping("/user/index.html")
    public String userIndex() {
        return "user/index";
    }

    /** Административная зона */
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