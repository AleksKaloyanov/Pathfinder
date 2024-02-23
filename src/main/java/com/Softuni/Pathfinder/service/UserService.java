package com.Softuni.Pathfinder.service;

import com.Softuni.Pathfinder.model.entity.UserEntity;
import com.Softuni.Pathfinder.model.service.UserServiceModel;

import java.util.Optional;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();
}
