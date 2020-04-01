/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.mail_sender;

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Tan
 */
public class MailSender implements Serializable {

    public static String sendVerifyCode(String email) throws Exception {
        final String username = "nhattan91099@gmail.com";
        final String password = "tandota123";
        Properties prop = System.getProperties();

        prop.put(
                "mail.smtp.auth", "true");
        prop.put(
                "mail.smtp.host", "smtp.gmail.com");
        prop.put(
                "mail.smtp.port", "587");
        prop.put(
                "mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        String alphaAndDigitString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        String randomCode;
        StringBuilder sb = new StringBuilder(7);
        Random rd = new Random();
        int randomPosition;
        for (int i = 0; i < 7; i++) {
            randomPosition = rd.nextInt(alphaAndDigitString.length());
            char randomChar = alphaAndDigitString.charAt(randomPosition);
            sb.append(randomChar);
        }
        randomCode = sb.toString();
        String textBody = "Your verification code: " + randomCode;
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Simple Blog's account verification code");
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        CommandMap.setDefaultCommandMap(mc);
        message.setContent(textBody, "text/html");
        Transport.send(message);

        return randomCode;
    }
}
