/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controllers;

import blog.dao.CommentDAO;
import blog.dto.CommentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tan
 */
public class ShowPostCommentController extends HttpServlet {

    private static final String SUCCESS = "post_detail.jsp";
    private static final String FAIL = "error.jsp";
    private static final Logger logger = Logger.getLogger(SearchPostByAdmin.class.getName());

    FileHandler fh;

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
        String url = FAIL;
        try {
            int postID = Integer.parseInt(request.getParameter("txtID"));
            CommentDAO dao = new CommentDAO();
            List<CommentDTO> list = dao.getCommentFromPost(postID);
            request.setAttribute("COMMENT", list);
            url = SUCCESS;
        } catch (Exception e) {
            log("ERROR AT SHOW POST COMMENT SERVLET " + e.getMessage());
            fh = new FileHandler("C:/Users/Tan/Desktop/Lab231/1/SimpleBlog/log/SimpleBlogLog.txt", 8096, 1, true);

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("ERROR AT Show New User Controller: " + e.getMessage());
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
