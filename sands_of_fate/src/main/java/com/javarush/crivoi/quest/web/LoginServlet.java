package com.javarush.crivoi.quest.web;

import java.io.IOException;
import java.io.PrintWriter;

import com.javarush.crivoi.quest.login.model.User;
import com.javarush.crivoi.quest.login.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String message = (String) req.getAttribute("message");
        String alertType = (String) req.getAttribute("alertType"); // e.g., "danger", "success"

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'><title>Login</title>");
            out.println("<link href='" + req.getContextPath() + "/css/style.css' rel='stylesheet'>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head><body>");

            // Main container with same card style as game
            out.println("<div class='container mt-5'>");
            out.println("<div class='card mx-auto shadow p-4' style='max-width: 500px;'>");

            out.println("<h2 class='mb-4 text-center'>Login or Register</h2>");

            // Display alert if message is available
            if (message != null && alertType != null) {
                out.printf("<div class='alert alert-%s'>%s</div>", alertType, message);
            }

            // Login/Register form inside the same card
            out.println("<form method='post'>");
            out.println("<input type='text' name='username' class='form-control mb-3' placeholder='Username' required>");
            out.println("<input type='password' name='password' class='form-control mb-3' placeholder='Password' required>");
            out.println("<div class='d-grid gap-2'>");
            out.println("<button type='submit' name='action' value='login' class='btn btn-primary btn-lg'>Login</button>");
            out.println("<button type='submit' name='action' value='register' class='btn btn-success btn-lg'>Register</button>");
            out.println("</div>");
            out.println("</form>");

            out.println("</div></div>"); // Close card + container

            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String action = req.getParameter("action");

        HttpSession session = req.getSession();

        if ("register".equals(action)) {
            // Try to register a new user
            if (UserRepository.register(username, password)) {
                session.setAttribute("user", username);
                resp.sendRedirect("game");
            } else {
                req.setAttribute("message", "⚠️ User already exists!");
                req.setAttribute("alertType", "warning");
                doGet(req, resp);
            }
        } else if ("login".equals(action)) {
            // Try to log in
            User user = UserRepository.login(username, password);
            if (user != null) {
                session.setAttribute("user", username);
                resp.sendRedirect("game");
            } else {
                req.setAttribute("message", "❌ Invalid credentials!");
                req.setAttribute("alertType", "danger");
                doGet(req, resp);
            }
        }
    }
}
