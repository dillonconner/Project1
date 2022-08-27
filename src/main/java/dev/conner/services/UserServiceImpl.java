package dev.conner.services;

import dev.conner.doas.UserDAO;
import dev.conner.entities.User;

public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) { this.userDAO = userDAO; }

    @Override
    public User createUser(User user) {
        return this.userDAO.createUser(user);
    }
}
