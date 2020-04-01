/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controllers;

import blog.dto.AccountDTO;
import java.io.IOException;
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
public class MainController extends HttpServlet {

     private static final Logger logger = Logger.getLogger(SearchPostByAdmin.class.getName());

    FileHandler fh;
    
    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String POST_DETAIL = "ShowPostDetailController";
    private static final String CREATE_POST = "CreatePostController";
    private static final String POST_COMMENT = "CreateCommentController";
    private static final String SHOW_ALL_POST = "ShowAllPostController";
    private static final String SEARCH = "SearchController";
    private static final String APPROVE = "ApprovePostController";
    private static final String DELETE = "DeletePostController";
    private static final String ADMIN_SEARCH = "SearchPostByAdmin";
    private static final String REGISTER = "RegisterController";
    private static final String VERIFY_CODE = "CheckVerifyCodeController";
    private static final String ACTIVE_USER = "ActiveUserController";
    private static final String SHOW_NEW_POST = "ShowNewPostController";
    private static final String LOGOUT = "LogoutController";
    private static final String SEND_VERIDY_CODE = "SendVerifyCodeController";
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
        String url = ERROR;
        HttpSession session = request.getSession();
        AccountDTO user = (AccountDTO) session.getAttribute("USER");
        try {
            String action = request.getParameter("action");
            if (action.equals("Login")) {
                    url = LOGIN;
            } else if (action.equals("ShowPostDetail")) {
                url = POST_DETAIL;
            } else if (action.equals("Post") && user.getRole().equals("2")) {
                url = CREATE_POST;
            } else if (action.equals("Comment")) {
                url = POST_COMMENT;
            } else if (action.equals("Search")) {
                url = SEARCH;
            } else if (action.equals("Approval") && user.getRole().equals("1")) {
                url = APPROVE;
            } else if (action.equals("Delete") && user.getRole().equals("1")) {
                url = DELETE;
            } else if (action.equals("Search based on Status") && user.getRole().equals("1")) {
                url = ADMIN_SEARCH;
            } else if (action.equals("Create Account")) {
                url = REGISTER;
            } else if (action.equals("Verify Account")) {
                url = VERIFY_CODE;
            } else if (action.equals("Active") && user.getRole().equals("1")) {
                url = ACTIVE_USER;
            } else if (action.equals("ShowNewPost")&& user.getRole().equals("1")) {
                url = SHOW_NEW_POST;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else if (action.equals("ShowAllPost")) {
                url = SHOW_ALL_POST;
            }else if (action.equals("SendVerifyCode")) {
                url = SEND_VERIDY_CODE;
            }else {
                request.setAttribute("ERROR", "INVALID ACTION");
                url = ERROR;
            }
        } catch (Exception e) {
            fh = new FileHandler("C:/Users/Tan/Desktop/Lab231/1/SimpleBlog/log/SimpleBlogLog.txt", 8096, 1, true);

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("ERROR at MainController " + e.getMessage());
            log("ERROR at MainController " + e.getMessage());
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
