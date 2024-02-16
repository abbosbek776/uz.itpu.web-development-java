import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
	private static final String COMMAND_PARAMETER_NAME = "command";
	private static final String USER_ROLE_ATTRIBUTE_NAME = "role";
	private static final String LOGIN_COMMAND = "login";
	private static final String LOGOUT_COMMAND = "logout";

	Map<String, Set<String>> roleCommands;
	// "Teacher", "viewSettings" , "updateSettings"
	// Student, ""viewSettings

	@Override
	public void init(FilterConfig config) throws ServletException {
		roleCommands = new HashMap<>();

		// TODO: Add your code here.
		Enumeration<String> parameterNames = config.getInitParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameter = parameterNames.nextElement();
			String[] paramValues = config.getInitParameter(parameter).split("\\s");
			roleCommands.put(parameter, Set.of(paramValues));
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// TODO: Add your code here.
		String command = httpRequest.getParameter(COMMAND_PARAMETER_NAME);
		String role = (String) httpRequest.getSession().getAttribute(USER_ROLE_ATTRIBUTE_NAME);
		Set<String> allowedCommands = roleCommands.get(role);
		if (allowedCommands != null && allowedCommands.contains(command)) {
			chain.doFilter(request, response);
		} else if (role == null) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
		if (allowedCommands == null && allowedCommands.contains(LOGOUT_COMMAND)) {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {

	}
}