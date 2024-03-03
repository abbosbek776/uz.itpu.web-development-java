package org.itpu.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    void run(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException ;
}
