package org.itpu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String productId = request.getParameter("productId");
    HttpSession session = request.getSession();

    // Add product to the cart (using a List as a basic example)
    List<String> cart = (List<String>) session.getAttribute("cart");
    if (cart == null) {
      cart = new ArrayList<>();
    }
    cart.add(productId);
    session.setAttribute("cart", cart);

    // Redirect back to the product page
    response.sendRedirect("index.jsp");
  }
}
