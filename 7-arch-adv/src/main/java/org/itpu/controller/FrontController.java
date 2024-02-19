package org.itpu.controller;

import org.itpu.model.GeneralModel;
import org.itpu.model.Model;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import static java.util.Objects.nonNull;

public class FrontController extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(FrontController.class.getName());

    private Model model;

    @Override
    public void init() {
        // todo init Model and resources

        model = new GeneralModel();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        justDo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        justDo(req, resp);
    }

    private void justDo(HttpServletRequest req, HttpServletResponse resp) {
        if (!validate(req, resp)) {
            LOGGER.warning("Request is not valid");
        } else {
            model.execute(req, resp);
        }

    }

    private boolean validate(HttpServletRequest req, HttpServletResponse resp) {
        boolean isValidRequest;
        isValidRequest = testNotNull(req, resp);
        // todo validate request and its params

        return isValidRequest;
    }

    private boolean testNotNull(HttpServletRequest req, HttpServletResponse resp) {
        return nonNull(req) && nonNull(resp);
    }
}