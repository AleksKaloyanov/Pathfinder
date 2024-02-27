package com.Softuni.Pathfinder.web;

import com.Softuni.Pathfinder.model.binding.RouteAddBindingModel;
import com.Softuni.Pathfinder.model.service.RouteServiceModel;
import com.Softuni.Pathfinder.model.view.RouteViewModel;
import com.Softuni.Pathfinder.service.RouteService;
import com.Softuni.Pathfinder.util.CurrentUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@RequestMapping("/routes")
@Controller
public class RouteController {

    private final RouteService routeService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public RouteController(RouteService routeService,
                           CurrentUser currentUser,
                           ModelMapper modelMapper) {
        this.routeService = routeService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String all(Model model) {

        model.addAttribute("routes",
                routeService.findAllRoutesView());

        return "routes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {

        model.addAttribute("route",routeService.findRouteById(id));

        return "route-details";
    }


    @GetMapping("/add")
    public String add() {
        if (currentUser.getId() == null) {
            return "redirect:/users/login";
        }
        return "add-route";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid RouteAddBindingModel routeAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes rAtt) throws IOException {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("routeAddBindingModel", routeAddBindingModel)
                    .addFlashAttribute("org.springframework.org.BindingResult.routeAddBindingModel", bindingResult);

            return "redirect:add";
        }

        RouteServiceModel routeServiceModel = modelMapper
                .map(routeAddBindingModel, RouteServiceModel.class);
        routeServiceModel.setGpxCoordinates(new String(routeAddBindingModel
                .getGpxCoordinates().getBytes()));

        routeService.addNewRoute(routeServiceModel);

        return "redirect:all";
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }

}
