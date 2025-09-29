package com.javarush.crivoi.quest.web;

import java.io.IOException;
import java.io.PrintWriter;

import com.javarush.crivoi.quest.login.model.User;
import com.javarush.crivoi.quest.login.repository.UserRepository;
import com.javarush.crivoi.quest.model.GameWeb;
import com.javarush.crivoi.quest.model.Node;
import com.javarush.crivoi.quest.model.Option;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private GameWeb game;

    @Override
    public void init() throws ServletException {
        super.init();
        game = new GameWeb(); 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Check if the user is logged in (session must exist and contain "user")
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }

        // Retrieve user from session
        String username = (String) session.getAttribute("user");
        User user = UserRepository.findByUsername(username);

        // If user clicked on an option → update progress
        String nodeId = req.getParameter("nodeId");
        if (nodeId != null) {
            user.setCurrentNodeId(nodeId);
            UserRepository.updateProgress(username, nodeId);
        } else {
            // Continue from last saved node
            nodeId = user.getCurrentNodeId();
        }

        Node currentNode = game.getNodes().get(nodeId);

        resp.setContentType("text/html; charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'><title>Пески Судьбы</title>");
            out.println("<link href='" + req.getContextPath() + "/css/style.css' rel='stylesheet'>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head><body>");

            // Main container
            out.println("<div class='container mt-5'>");
            out.println("<div class='card mx-auto shadow p-4' style='max-width: 700px;'>");

            // Display logged in user
            out.println("<h5 class='text-end text-muted'>Logged in as: " + user.getUsername() + "</h5>");

            // Current node text
            out.println("<h2 class='mb-4'>" + currentNode.getText() + "</h2>");

            if (currentNode.getOptions().isEmpty()) {
                // End of the game
                out.println("<p><b>Game Over!</b></p>");
                out.println("<a href='" + req.getContextPath() + "/game?nodeId=1' class='btn btn-success mt-3'>Restart</a>");
            } else {
                // Show options as buttons
                out.println("<div class='d-grid gap-3'>");
                for (Option option : currentNode.getOptions()) {
                    out.println("<a href='" + req.getContextPath() + "/game?nodeId=" + option.getIdNextNode() + "' class='btn btn-primary btn-lg'>"
                            + option.getText() + "</a>");
                }
                out.println("</div>");
            }

            // Logout button
            out.println("<a href='" + req.getContextPath() + "/logout' class='btn btn-danger mt-3'>Logout</a>");

            out.println("</div></div>"); // Close card + container
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body></html>");
        }
    }
}
