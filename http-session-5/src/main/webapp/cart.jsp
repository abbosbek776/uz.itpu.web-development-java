<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<String> cart = Optional.ofNullable((List<String>) session.getAttribute("cart")).orElse(Collections.emptyList());
    int cartSize = cart.size();
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <header>
        <h1>Shopping Cart</h1>
        <a href="index.jsp">Back to Products</a>
    </header>

    <main>
        <section class="cart">
            <h2>Your Cart</h2>
            <p>Items: <%= cart %></p>

            <!-- Display cart items here -->
            <ul>
                <%
                    for (String productId : cart) {
                        String glyph = " ";
                        if (productId.equals("1")) {
                            glyph = "🥤";
                        } else {
                            glyph = "🥛";
                        }
                %>
                <li><span class="glyph"><%= glyph %></span> Product <%= productId %></li>
                <%
                    }
                %>
            </ul>
        </section>
    </main>
</body>
</html>
