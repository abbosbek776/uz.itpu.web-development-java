package org.itpu.controller.impl;

import org.itpu.controller.Command;
import org.itpu.domain.User;
import org.itpu.service.UserService;
import org.itpu.service.UserServiceImpl;
import org.itpu.service.UserServiceV2Impl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.itpu.Constant.*;
import static org.itpu.Constant.PASSWORD2;

public class Register implements Command {

    private final UserService userService = UserServiceV2Impl.getInstance();

    @Override
    public void run(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean passwordEquals = req.getParameter(PASSWORD1).equals(req.getParameter(PASSWORD2));

        if (passwordEquals) {

            User user = new User();
            user.setName(req.getParameter(NAME));
            user.setSurname(req.getParameter(SURNAME));
            user.setEmail(req.getParameter(EMAIL));
            user.setPhoneNumber(req.getParameter(PHONE_NUMBER));
            user.setLogin(req.getParameter(LOGIN));
            user.setPassword(req.getParameter(PASSWORD1).toCharArray());

            System.out.println(user);
            userService.registerUser(user);
        } else {
            req.setAttribute(INFO, "passwordEquals is " + passwordEquals);
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
