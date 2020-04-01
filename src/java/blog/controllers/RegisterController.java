/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controllers;

import blog.dao.AccountDAO;
import blog.dto.AccountErrorObject;
import blog.mail_sender.MailSender;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author Tan
 */
public class RegisterController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SearchPostByAdmin.class.getName());

    FileHandler fh;
    private static final String SUCCESS = "login.jsp";
    private static final String FAIL = "error.jsp";
    private static final String INVALID = "register.jsp";
    private static final String INPUT_CODE = "confirm_code.jsp";

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
        AccountErrorObject aeo = new AccountErrorObject();

        try {
            boolean valid = true;
            String availableEmail = request.getParameter("avaiChk");
            String email = request.getParameter("txtEmail");
            String name = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            if (email.length() == 0) {
                valid = false;
                aeo.setEmailError("Your email is empty");
            }
            if (email.length() < 10) {
                valid = false;
                aeo.setEmailError("Invalid email input (abc@gmail.com)");

            }
            if (name.length() == 0) {
                valid = false;
                aeo.setNameError("Your name is empty");
            }
            if (password.length() < 6) {
                valid = false;
                aeo.setPasswordError("Password length should be more than 6 character");
            }
            if (valid) {
                boolean chk = true;
                if (email.contains("@")) {
                    int count = 0;
                    if (email.charAt(0) == '@') {
                        chk = false;
                        aeo.setEmailError("Invalid email input (abc@gmail.com)");
                    }
                    for (int i = 0; i < email.length(); i++) {
                        if (email.charAt(i) == '@') {
                            count++;
                        }
                    }
                    if (count > 1) {
                        chk = false;
                        aeo.setEmailError("Invalid email input (abc@gmail.com)");
                    }
                    if (!email.subSequence(email.length() - 10, email.length()).equals("@gmail.com")) {
                        chk = false;
                        aeo.setEmailError("Invalid email input (abc@gmail.com)");

                    }
                    if (chk) {
                        boolean check = false;
                        AccountDAO dao = new AccountDAO();
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] byteOfText = password.getBytes(StandardCharsets.UTF_8);
                        byte[] hashedByteOfText = digest.digest(byteOfText);
                        String encoded = Base64.getEncoder().encodeToString(hashedByteOfText);

                        if (availableEmail.equals("yes")) {
                            check = dao.registAvailableAccount(email, name, encoded);
                            if (check) {
                                request.setAttribute("EMAIL", email);

                                MailSender mailSender = new MailSender();
                                String code = mailSender.sendVerifyCode(email);
                                request.setAttribute("CODE", code);
                                url = INPUT_CODE;
                            } else {
                                request.setAttribute("ERROR", "ERROR WHEN CREATING AVAILABLE ACCOUNT");
                            }

                        } else {
                            check = dao.registAccount(email, name, encoded);
                            if (check) {
                                url = SUCCESS;
                                request.setAttribute("STATUS", "Your account is registed, wait for admin to confirm");
                            } else {
                                request.setAttribute("ERROR", "ERROR WHEN CREATING ACCOUNT");
                            }

                        }

                    } else {
                        url = INVALID;
                        request.setAttribute("INVALID", aeo);
                    }
                } else {
                    url = INVALID;
                    aeo.setEmailError("Invalid email input (abc@gmail.com)");
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
            logger.info("ERROR at  REGISTER CONTROLLER: " + e.getMessage());
            log("ERROR AT REGISTER CONTROLLER: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                url = INVALID;
                aeo.setEmailError("Your email is existed");
                request.setAttribute("INVALID", aeo);
            }
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
