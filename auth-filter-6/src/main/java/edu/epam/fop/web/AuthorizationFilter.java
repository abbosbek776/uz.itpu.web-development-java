package edu.epam.fop.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// TODO: Add your code here.

	}
}