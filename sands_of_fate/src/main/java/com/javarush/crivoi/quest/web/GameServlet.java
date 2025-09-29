package com.javarush.crivoi.quest.web;

import java.io.IOException;
import java.io.PrintWriter;

import com.javarush.crivoi.quest.model.GameWeb;
import com.javarush.crivoi.quest.model.Node;
import com.javarush.crivoi.quest.model.Option;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        resp.setContentType("text/html; charset=UTF-8");

        String nodeId = req.getParameter("nodeId");
        if (nodeId == null) {
            nodeId = "1"; // start node
        }

        Node currentNode = game.getNodes().get(nodeId);

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'><title>Пески Судьбы</title>");
            out.println("<link href='" + req.getContextPath() + "/css/style.css' rel='stylesheet'>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head><body>");

            // Main container
            out.println("<div class='container mt-5'>");
            out.println("<div class='card mx-auto shadow p-4' style='max-width: 700px;'>");

            // Node text
            out.println("<h2 class='mb-4'>" + currentNode.getText() + "</h2>");

            if (currentNode.getOptions().isEmpty()) {
                out.println("<p><b>Игра окончена!</b></p>");
                out.println("<a href='" + req.getContextPath() + "/game' class='btn btn-success mt-3'>Начать заново</a>");
            } else {
                out.println("<div class='d-grid gap-3'>");
                for (Option option : currentNode.getOptions()) {
                    out.println("<a href='" + req.getContextPath() + "/game?nodeId=" + option.getIdNextNode() + "' class='btn btn-primary btn-lg'>"
                            + option.getText() + "</a>");
                }
                out.println("</div>");
            }

            out.println("</div></div>"); // card + container
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body></html>");
        }
    }
}
