package edu.epam.fop.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class AuthorizationFilter implements Filter {
	private static final String COMMAND_PARAMETER_NAME = "command";
	private static final String USER_ROLE_ATTRIBUTE_NAME = "role";
	private static final String LOGIN_COMMAND = "login";
	private static final String LOGOUT_COMMAND = "logout";

	Map<String, Set<String>> roleCommands;

	@Override
	public void init(FilterConfig config) throws ServletException {
		roleCommands = new HashMap<>();

		// TODO: Add your code here.
		Enumeration<String> initParameterNames = config.getInitParameterNames();
		while (initParameterNames.hasMoreElements()) {
			String initParameterName = initParameterNames.nextElement();
			String[] initParameterArray = config.getInitParameter(initParameterName).split(" ");
			roleCommands.put(initParameterName, Set.of(initParameterArray));
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// TODO: Add your code here.
		String userRole = null;
		String command = httpRequest.getParameter(COMMAND_PARAMETER_NAME);
		HttpSession session = httpRequest.getSession();
		if (nonNull(session)) {
			userRole = (String) session.getAttribute(USER_ROLE_ATTRIBUTE_NAME);
		}

		if (isNull(session) || isNull(userRole)) {
			if (LOGIN_COMMAND.equalsIgnoreCase(command)) {
				chain.doFilter(request, response);
			}
		} else {
			if (nonNull(userRole)) {
				if (LOGOUT_COMMAND.equalsIgnoreCase(command)) {
					chain.doFilter(request, response);
				} else if (nonNull(userRole)
						&& nonNull(command)
						&& nonNull(roleCommands.get(userRole))
						&& roleCommands.get(userRole).contains(command)) {
					chain.doFilter(request, response);
				}
			}
		}
		httpServletResponse.sendError(403);
	}
}