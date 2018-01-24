package skleppie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import skleppie.model.User;
import skleppie.service.UserService;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ModelAndView index(@RequestParam(required = false, value = "msg") String msg) {
        ModelAndView modelAndView = new ModelAndView();

        String username = getUserFirstName();

        modelAndView.addObject("userName", getUserFirstName());
        modelAndView.addObject("message", getHomepageMessage(msg));
        modelAndView.setViewName("index");

        return modelAndView;
    }

    private String getUserFirstName() {
        String userFirstName = "";
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(username.equals("anonymousUser")) {
            return userFirstName;
        }

        User user = userService.findUserByEmail(username);
        userFirstName = user.getFirstName();

        return userFirstName;
    }

    private String getHomepageMessage(String msg) {
        String message = "";
        if(msg == null) {
            return message;
        }else if(msg.equals("login")) {
            message = "Zostałeś zalogowany";
        }else if(msg.equals("logout")) {
            message = "Zostałeś wylogowany";
        }

        return message;
    }

}

