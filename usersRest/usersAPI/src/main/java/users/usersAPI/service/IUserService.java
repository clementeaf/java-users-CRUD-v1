package users.usersAPI.service;

import users.usersAPI.model.dto.UserRequest;
import users.usersAPI.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserRequest> getAllUsers();

    UserRequest getUser(int id);

    UserRequest saveUser(User user);

    UserRequest updateUser(User userRequest, int id);
    String deleteUser(int id);
}
