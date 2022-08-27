package dev.conner.controllers;

import com.google.gson.Gson;
import dev.conner.entities.User;
import dev.conner.services.UserService;
import io.javalin.http.Handler;

public class UserController {
    private Gson gson = new Gson();
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Handler createUser = (ctx) -> {
        System.out.println(ctx.body());
        User u = this.gson.fromJson(ctx.body(), User.class);

        try{
            u = this.userService.createUser(u);
            ctx.status(201);
            ctx.result(gson.toJson(u));
        }catch (RuntimeException e){
            ctx.status(400);
            ctx.result("ERROR");
        }
    };
}
