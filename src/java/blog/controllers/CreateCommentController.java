/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controllers;

import blog.dao.CommentDAO;
import blog.dto.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tan
 */
public class CreateCommentController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SearchPostByAdmin.class.getName());

    FileHandler fh;
    private static final String SUCCEED = "ShowPostDetailController";
    private static final String FAILED = "error.jsp";
    private static final String INVALID = "ShowPostDetailController";
    private static final String UNAUTHORIZED = "login.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAILED;
        try {
            HttpSession session = request.getSession();
            AccountDTO user = (AccountDTO) session.getAttribute("USER");
            if (user == null) {
                url = UNAUTHORIZED;
            } else if (user.getRole().equals("1")) {
                url = FAILED;
                request.setAttribute("ERROR", "This function is for member only");
            } else {
                boolean valid = true;

                String comment = request.getParameter("txtComment");
                if (comment.length() == 0) {
                    valid = false;
                }
                int pID = Integer.parseInt(request.getParameter("txtPID"));
                String aID = request.getParameter("txtAID");
                if (valid) {
                    CommentDAO dao = new CommentDAO();
                    boolean chk = dao.createComment(pID, aID, comment);
                    if (chk) {
                        url = SUCCEED;
                    } else {
                        url = FAILED;
                        request.setAttribute("ERRROR", "FAILED WHEN CREATE COMMENT");
                    }
                } else {
                    url = INVALID;
                    request.setAttribute("INVALID", "Your comment is empty");
                }
            }

        } catch (Exception e) {
                fh = new FileHandler("C:/Users/Tan/Desktop/Lab231/1/SimpleBlog/log/SimpleBlogLog.txt", 8096, 1, true);

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("ERROR AT CREATE COMMENT CONTROLLER: " + e.getMessage());
            log("ERROR AT CREATE COMMENT CONTROLLER: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
