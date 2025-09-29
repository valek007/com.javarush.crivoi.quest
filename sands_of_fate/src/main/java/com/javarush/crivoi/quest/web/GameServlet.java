package com.javarush.crivoi.quest.web;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javarush.crivoi.quest.model.game.Game;
import com.javarush.crivoi.quest.model.game.Node;
import com.javarush.crivoi.quest.model.game.Option;
import com.javarush.crivoi.quest.model.login.User;
import com.javarush.crivoi.quest.model.login.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Game game;

    private static final Logger logger = LoggerFactory.getLogger(GameServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        game = new Game();
        logger.info("GameServlet initialized, game instance created");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            logger.warn("No active session or user not logged in, redirecting to login");
            resp.sendRedirect("login");
            return;
        }

        String username = (String) session.getAttribute("user");
        User user = UserRepository.findByUsername(username);
        logger.info("User '{}' retrieved from session", username);

        String nodeId = req.getParameter("nodeId");
        if (nodeId != null) {
            user.setCurrentNodeId(nodeId);
            UserRepository.updateProgress(username, nodeId);
            logger.info("User '{}' moved to node '{}'", username, nodeId);
        } else {
            nodeId = user.getCurrentNodeId();
            logger.info("User '{}' continues from last saved node '{}'", username, nodeId);
        }

        Node currentNode = game.getNodes().get(nodeId);
        logger.debug("Displaying node '{}' with text: {}", nodeId, currentNode.getText());

        resp.setContentType("text/html; charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'><title>Пески Судьбы</title>");
            out.println("<link href='" + req.getContextPath() + "/css/style.css' rel='stylesheet'>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head><body>");

            out.println("<div class='container mt-5'>");
            out.println("<div class='card mx-auto shadow p-4' style='max-width: 700px;'>");

            out.println("<h5 class='text-end text-muted'>Logged in as: " + user.getUsername() + "</h5>");
            out.println("<h2 class='mb-4'>" + currentNode.getText() + "</h2>");
            logger.info("Rendered node '{}' content for user '{}'", nodeId, username);

            if (currentNode.getOptions().isEmpty()) {
                out.println("<p><b>Game Over!</b></p>");
                out.println("<a href='" + req.getContextPath() + "/game?nodeId=1' class='btn btn-success mt-3'>Restart</a>");
                logger.info("User '{}' reached end of game at node '{}'", username, nodeId);
            } else {
                out.println("<div class='d-grid gap-3'>");
                for (Option option : currentNode.getOptions()) {
                    out.println("<a href='" + req.getContextPath() + "/game?nodeId=" + option.getIdNextNode() + "' class='btn btn-primary btn-lg'>"
                            + option.getText() + "</a>");
                    logger.debug("Displayed option '{}' -> node '{}'", option.getText(), option.getIdNextNode());
                }
                out.println("</div>");
            }

            out.println("<a href='" + req.getContextPath() + "/logout' class='btn btn-danger mt-3'>Logout</a>");
            out.println("</div></div>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body></html>");

            logger.info("Page rendered successfully for user '{}'", username);
        }
    }
}
