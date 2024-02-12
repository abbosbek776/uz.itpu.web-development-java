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
    <title>Simple E-commerce</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <header>
        <h1>Simple E-commerce</h1>
        <a href="cart.jsp">View Cart (<%= cartSize %>)</a>
    </header>

    <main>
        <section class="product-list">
            <%-- Product 1 --%>
            <div class="product">
                <span class="glyph">🥤</span>
                <p>Soda</p>
                <form action="AddToCartServlet" method="post">
                    <input type="hidden" name="productId" value="1">
                    <input type="submit" value="Add to Cart">
                </form>
            </div>

            <%-- Product 2 --%>
            <div class="product">
                <span class="glyph">🥛</span>
                <p>Milk</p>
                <form action="AddToCartServlet" method="post">
                    <input type="hidden" name="productId" value="2">
                    <input type="submit" value="Add to Cart">
                </form>
            </div>

            <%-- Add more products as needed --%>
        </section>
    </main>
</body>
</html>
