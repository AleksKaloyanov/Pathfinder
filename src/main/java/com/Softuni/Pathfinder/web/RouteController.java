package com.Softuni.Pathfinder.web;

import com.Softuni.Pathfinder.model.view.RouteViewModel;
import com.Softuni.Pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/routes")
@Controller
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public String all(Model model) {

        model.addAttribute("routes",
                routeService.findAllRoutesView());

        return "routes";
    }


    @GetMapping("/add")
    public String add() {
        return "add-route";
    }

}
