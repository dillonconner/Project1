package dev.conner.doas;

import dev.conner.entities.User;

import java.util.Set;

public interface UserDAO {

    User createUser(User user);
    Set<User> getAllUsers();

}
