/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controllers;

import blog.dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CheckVerifyCodeController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SearchPostByAdmin.class.getName());

    FileHandler fh;
    private static final String SUCCESS = "login.jsp";
    private static final String FAIL = "error.jsp";
    private static final String WRONG = "confirm_code.jsp";

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
            String email = request.getParameter("txtEmail");
            String code = request.getParameter("txtCode");
            String userInput = request.getParameter("txtInput");
            if (code.matches(userInput)) {
                url = SUCCESS;
                request.setAttribute("STATUS", "Your account is activated");
                AccountDAO dao = new AccountDAO();
                dao.activeUser(email);
            } else {
                url = WRONG;
                request.setAttribute("WRONG", "Your code is wrong, please try again!!!");
                request.setAttribute("EMAIL", email);
                request.setAttribute("CODE", code);
            }
        } catch (Exception e) {
            fh = new FileHandler("C:/Users/Tan/Desktop/Lab231/1/SimpleBlog/log/SimpleBlogLog.txt", 8096, 1, true);

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("Error at Check Verify Code Controller: " + e.getMessage());
            log("Error at Check Verify Code Controller: " + e.getMessage());
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
