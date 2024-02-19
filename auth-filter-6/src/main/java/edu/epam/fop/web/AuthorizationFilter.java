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

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// TODO: Add your code here.
		String command = httpServletRequest.getParameter(COMMAND_PARAMETER_NAME);
		HttpSession session = httpServletRequest.getSession();
		String userRole = isNull(session) ? null : (String) session.getAttribute(USER_ROLE_ATTRIBUTE_NAME);

		if (isAnonymousOrGuest(session, userRole)
				&& LOGIN_COMMAND.equalsIgnoreCase(command)) {
			proceed(request, response, chain);

		} else if (hasRoleAndCommand(userRole, command)) {
			if (LOGOUT_COMMAND.equalsIgnoreCase(command)) {
				proceed(request, response, chain);

			} else if (isCommandAllowedForRole(command, userRole)) {
				proceed(request, response, chain);
			}
		}

		httpServletResponse.sendError(403);
	}

	private void proceed(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	private boolean hasRoleAndCommand(String userRole, String command) {
		return nonNull(userRole) && nonNull(command);
	}

	private boolean isAnonymousOrGuest(HttpSession session, String userRole) {
		return isNull(session) || isNull(userRole);
	}

	private boolean isCommandAllowedForRole(String command, String userRole) {
		return nonNull(roleCommands.get(userRole))
				&& roleCommands.get(userRole).contains(command);
	}
}