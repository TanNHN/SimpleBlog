/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controllers;

import blog.dao.PostDAO;
import blog.dto.AccountDTO;
import blog.dto.PostErrorObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class CreatePostController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SearchPostByAdmin.class.getName());

    FileHandler fh;
    private static final String SUCCESS = "write_post.jsp";
    private static final String FAIL = "error.jsp";
    private static final String INVALID = "write_post.jsp";

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
            PostErrorObject peo = new PostErrorObject();
            boolean valid = true;
            HttpSession session = request.getSession();
            AccountDTO user = (AccountDTO) session.getAttribute("USER");
            Date date = new Date();
            String title = request.getParameter("txtTitle");
            String shortDescription = request.getParameter("txtShortDescription");
            String content = request.getParameter("txtContent");
            if (title.length() == 0) {
                valid = false;
                peo.setTitleError("Your title is empty");
            }
            if (shortDescription.length() == 0) {
                valid = false;
                peo.setShortDescriptionError("Your Short Description is emoty");
            }
            if (content.length() == 0) {
                valid = false;
                peo.setContentError("Your Content is empty");
            }
            if (valid) {
                PostDAO dao = new PostDAO();
                boolean test = dao.createPost(user.getEmail(), title, shortDescription, content, date);
                if (test) {
                    request.setAttribute("POSTED", "Your post is created and waiting for admin to active your post");
                    url = SUCCESS;
                } else {
                    url = FAIL;
                    request.setAttribute("ERROR", "Failed when create your post");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", peo);
            }
        } catch (Exception e) {
             fh = new FileHandler("C:/Users/Tan/Desktop/Lab231/1/SimpleBlog/log/SimpleBlogLog.txt", 8096, 1, true);

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("ERROR AT CREATE POST CONTROLLER " + e.getMessage());
            log("ERROR AT CREATE POST CONTROLLER " + e.getMessage());
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
