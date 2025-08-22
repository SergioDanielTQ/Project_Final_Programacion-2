package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Product;
import services.ProductService;
import services.ProductServiceImpl;


@WebServlet({"/product"})
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductService service = new ProductServiceImpl();
        List<Product> productList = service.getAllProducts();
        request.setAttribute("productList", productList);
        getServletContext().getRequestDispatcher("/ProductList.jsp").forward(request, response);
    }

}
