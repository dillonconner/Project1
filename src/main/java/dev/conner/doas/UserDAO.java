package dev.conner.doas;

import dev.conner.entities.User;

public interface UserDAO {

    User createUser(User user);
    User getUserByName(String username);

}
