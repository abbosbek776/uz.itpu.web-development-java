package org.itpu.service.commandImpl;

import org.itpu.service.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration implements Command {

    //UserService userService = new UserServiceImpl;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        //.. get registartion params
        //.. init user with received parameters
        //.. userService.register(user)
        //.. req.forward()
    }
}
