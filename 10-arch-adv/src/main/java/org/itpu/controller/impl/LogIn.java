package org.itpu.controller.impl;

import org.itpu.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.itpu.Constant.*;

public class LogIn implements Command {
    @Override
    public void run(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        char[] password = req.getParameter(PASSWORD).toCharArray();

        boolean isLogged = true;
//        isLogged = userService.logIn(login, password);

        if (isLogged) {
            req.getSession(true).setAttribute(ISLOGGED, isLogged);
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
