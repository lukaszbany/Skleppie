package skleppie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import skleppie.model.User;
import skleppie.service.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView addNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("registration");
        User existingUser = userService.findUserByEmail(user.getEmail());

        if(existingUser != null) {
            bindingResult.rejectValue("email", "error.user", "Ten email jest już w bazie");
        }

        if(!bindingResult.hasErrors()) {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Twoje konto zostało utworzone");
            modelAndView.addObject("user", new User());
        }

        return modelAndView;
    }
}
