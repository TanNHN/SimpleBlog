/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controllers;

import blog.dao.AccountDAO;
import blog.dto.AccountDTO;
import blog.dto.AccountErrorObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
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
public class LoginController extends HttpServlet {
private static final Logger logger = Logger.getLogger(SearchPostByAdmin.class.getName());

    FileHandler fh;
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "main_page.jsp";
    private static final String INVALID = "login.jsp";
    private static final String ERROR = "error.jsp";

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
        try {
            HttpSession session = request.getSession();
            AccountErrorObject aeo = new AccountErrorObject();

            boolean valid = true;
            AccountDTO account;
            String id = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            if (id.length() == 0) {
                valid = false;
                aeo.setEmailError("Your Email is empty");
            }
            if (password.length() == 0) {
                valid = false;
                aeo.setPasswordError("Your Password is empty");
            }
            if (valid) {
                boolean chk = true;
                if (id.contains("@")) {
                    int count = 0;
                    if (id.charAt(0) == '@') {
                        chk = false;
                        aeo.setEmailError("Invalid Email input");
                    }
                    if (id.charAt(id.length() - 1) == '@') {
                        chk = false;
                        aeo.setEmailError("Invalid Email input");
                    }
                    for (int i = 0; i < id.length(); i++) {
                        if (id.charAt(i) == '@') {
                            count++;
                        }
                    }
                    if (count > 1) {
                        chk = false;
                        aeo.setEmailError("Invalid Email input");
                    }
                } else {
                    chk = false;
                    aeo.setEmailError("Invalid Email input");
                }
                if (chk) {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] byteOfText = password.getBytes(StandardCharsets.UTF_8);
                    byte[] hashedByteOfText = digest.digest(byteOfText);
                    String encoded = Base64.getEncoder().encodeToString(hashedByteOfText);

                    AccountDAO accountDAO = new AccountDAO();
                    account = accountDAO.checkLogin(id, encoded);
                    if (account != null) {
                        if (account.getStatus() == 2) {
                            session.setAttribute("USER", account);

                            if (account.getRole().equals("1")) {
                                url = ADMIN;
                            } else if (account.getRole().equals("2")) {
                                url = USER;
                            } else {
                                url = ERROR;
                                request.setAttribute("ERROR", "Your role is invalid");
                            }
                        } else if (account.getStatus() == 1) {
                            url = INVALID;
                            aeo.setEmailError("Your account has not been active");
                            request.setAttribute("INVALID", aeo);
                        } else if (account.getStatus() == 3) {
                            url = INVALID;
                            aeo.setEmailError("Your account has not been active, click here to re-send code: " + "<a href=\"" + "MainController?action=SendVerifyCode&txtMail=" + id + "\">" + "resend" + "</a>");
                            request.setAttribute("INVALID", aeo);
                        }

                    } else {
                        url = ERROR;
                        request.setAttribute("ERROR", "Wrong email or password ");
                    }

                } else {
                    url = INVALID;
                    request.setAttribute("INVALID", aeo);
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", aeo);
            }
        } catch (Exception e) {
            fh = new FileHandler("C:/Users/Tan/Desktop/Lab231/1/SimpleBlog/log/SimpleBlogLog.txt", 8096, 1, true);

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("ERROR AT LOGIN CONTROLLER: " + e.getMessage());
            log("ERROR AT LOGIN CONTROLLER: " + e.getMessage());
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
