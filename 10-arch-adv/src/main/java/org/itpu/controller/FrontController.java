package org.itpu.controller;

import org.itpu.controller.impl.LogIn;
import org.itpu.controller.impl.LogOut;
import org.itpu.controller.impl.Register;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.itpu.Constant.*;


public class FrontController extends HttpServlet {

    private final Map<String, Command> cammandMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        cammandMap.put(REGISTER_COMMAND, new Register());
        cammandMap.put(LOGIN_COMMAND, new LogIn());
        cammandMap.put(LOG_OUT_COMMAND, new LogOut());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("msg", "my cmd");

        final String commandName = req.getParameter(COMMAND);
        final Command command = cammandMap.get(commandName);

        if (command == null) {
            req.setAttribute(INFO, "NO COMMAND");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            command.run(req, resp);
        }
    }
}