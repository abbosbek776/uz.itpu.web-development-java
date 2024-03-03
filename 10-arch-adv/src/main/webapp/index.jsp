<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<html>
    <body>
        <h2>Hello View!</h2>

        <p>Info: ${info}</p>

        <p>${msg}</p>

        <section id="register-section">
            <form method="post" action="controller">
                <input type="hidden" name="command" value="register-command" />
                <label>Name</label>
                <input type="text" name="name" />
                <label>Surname</label>
                <input type="text" name="surname" />
                <label>Email</label>
                <input type="text" name="email" />
                <label>Phone number</label>
                <input type="text" name="phone-number" />
                <label>Login</label>
                <input type="text" name="login" />
                <label>Password 1</label>
                <input type="password" name="password1" />
                <label>Password 2</label>
                <input type="password" name="password2" />

                <button type="submit">Submit</button>
            </form>
        </section>

        <section id="login-section">
            <form method="post" action="controller">
                <input type="hidden" name="command" value="login-command" />
                <label>Login</label>
                <input type="text" name="login" />
                <label>Password</label>
                <input type="password" name="password" />
                <button type="submit">Log in</button>
            </form>
        </section>

        <section id="log-out-section">
            <form method="post" action="controller">
                <input type="hidden" name="command" value="log-out-command" />
                <input type="hidden" name="command" value="log-out"/>
                <button type="submit">Log out</button>
            </form>
        </section>

        <section id="logged-in-status-section">
            <p>is logged in ${sessionScope.isLogged}</p>
        </section>
    </body>
</html>
