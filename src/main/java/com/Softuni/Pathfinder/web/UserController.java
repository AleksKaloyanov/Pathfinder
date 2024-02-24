package com.Softuni.Pathfinder.web;

import com.Softuni.Pathfinder.model.binding.UserLoginBindingModel;
import com.Softuni.Pathfinder.model.binding.UserRegisterBindingModel;
import com.Softuni.Pathfinder.model.service.UserServiceModel;
import com.Softuni.Pathfinder.model.view.UserViewModel;
import com.Softuni.Pathfinder.service.UserService;
import com.Softuni.Pathfinder.util.CurrentUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes rAtt) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {
            rAtt
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);

            return "redirect:register";
        }

        boolean isNameExists = userService.isNameExists(userRegisterBindingModel.getUsername());

        if (isNameExists) {
            //ToDo: redirect with message
        }
        userService.registerUser(modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("isExist", true);

        return "/login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel"
                            , userLoginBindingModel);

            return "redirect:login";
        }

        UserServiceModel user = userService
                .findUserByUsernameAndPassword
                        (userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (user == null) {
            rAtt
                    .addFlashAttribute("isExist", false)
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.org.BindingResult.userLoginBindingModel",
                            bindingResult);

            return "redirect:login";
        }

        userService.loginUser(user.getId(), user.getUsername());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    private String profile(@PathVariable Long id, Model model) {

        model.addAttribute("user", modelMapper
                .map(userService.findById(id), UserViewModel.class));

        return "profile";
    }
}
