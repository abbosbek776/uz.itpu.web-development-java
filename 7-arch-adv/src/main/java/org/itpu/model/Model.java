package org.itpu.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Model {
    void execute(HttpServletRequest req, HttpServletResponse resp);
}
