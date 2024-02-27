package com.Softuni.Pathfinder.service;

import com.Softuni.Pathfinder.model.service.RouteServiceModel;
import com.Softuni.Pathfinder.model.view.RouteDetailsViewModel;
import com.Softuni.Pathfinder.model.view.RouteViewModel;

import java.util.List;

public interface RouteService {
    List<RouteViewModel> findAllRoutesView();

    void addNewRoute(RouteServiceModel routeServiceModel);

    RouteDetailsViewModel findRouteById(Long id);
}
