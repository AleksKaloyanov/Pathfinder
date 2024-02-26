package com.Softuni.Pathfinder.service.impl;

import com.Softuni.Pathfinder.model.entity.RouteEntity;
import com.Softuni.Pathfinder.model.service.RouteServiceModel;
import com.Softuni.Pathfinder.model.view.RouteViewModel;
import com.Softuni.Pathfinder.repository.RouteRepository;
import com.Softuni.Pathfinder.service.CategoryService;
import com.Softuni.Pathfinder.service.RouteService;
import com.Softuni.Pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public RouteServiceImpl(RouteRepository routeRepository,
                            UserService userService,
                            CategoryService categoryService,
                            ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteViewModel> findAllRoutesView() {
        return routeRepository
                .findAll()
                .stream()
                .map(route ->
                {
                    RouteViewModel routeViewModel = modelMapper.map(route, RouteViewModel.class);
                    if (route.getPictures().isEmpty()) {
                        routeViewModel.setPictureUrl("/images/pic4.jpg");
                    } else {
                        routeViewModel
                                .setPictureUrl(route
                                        .getPictures()
                                        .stream()
                                        .findFirst()
                                        .get()
                                        .getUrl());
                    }

                    return routeViewModel;
                })
                .collect(Collectors.toList());

    }

    @Override
    public void addNewRoute(RouteServiceModel routeServiceModel) {
        RouteEntity route = modelMapper.map(routeServiceModel, RouteEntity.class);
        route.setAuthor(userService.findCurrentLoginUserEntity());

        route.setCategories(routeServiceModel
                .getCategories()
                .stream()
                .map(categoryService::findCategoryByName)
                .collect(Collectors.toSet()));

        routeRepository.save(route);
    }
}
