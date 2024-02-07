package org.itpu.controller;

import org.itpu.exception.MyCustomException;
import org.itpu.service.Calculator;
import org.itpu.service.calculatorImpl.Subtract;
import org.itpu.service.calculatorImpl.Sum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FrontController extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(FrontController.class.getName());
    public static final String COMMAND = "command", OPERATION = "operation", SUM = "sum", SUBTRACT = "subtract", NUMBER_1 = "number-1", NUMBER_2 = "number-2";
    public Map<String, Calculator> commands = new HashMap<>();

    @Override
    public void init() {
        commands.put(SUM, new Sum());
        commands.put(SUBTRACT, new Subtract());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
//        final String commandTitle = req.getParameter(COMMAND).trim();
//        LOGGER.info("CommandTitle=" + commandTitle);
            final String operation = req.getParameter(OPERATION).trim();

            Calculator calculator = commands.get(operation);
            calculator.calculate(req, resp);
        } catch (MyCustomException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String command = req.getParameter("command");
        final String message = req.getParameter("message");
        LOGGER.info("Received post method got : " + command);
        LOGGER.info("Received post method got : " + message);
        req.setAttribute("result", "Received is [" + command + ", " + message + "]");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
