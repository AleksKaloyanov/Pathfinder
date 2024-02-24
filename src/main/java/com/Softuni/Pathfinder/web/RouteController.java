package com.Softuni.Pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/routes")
@Controller
public class RouteController {

    @GetMapping("/all")
    public String all() {
        return "routes";
    }

}
