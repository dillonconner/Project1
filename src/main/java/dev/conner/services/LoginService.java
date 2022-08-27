package dev.conner.services;

import dev.conner.entities.User;

public interface LoginService {

    User validateUser(String username, String password);
}
