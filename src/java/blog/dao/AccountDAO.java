/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.dto.AccountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tan
 */
public class AccountDAO {

    Connection cnn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void closeConnection() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public AccountDTO checkLogin(String id, String password) throws Exception {
        AccountDTO account = null;
        cnn = blog.db.DBUtil.getConnection();
        if (cnn != null) {
            String sql = "SELECT [Name],[Role],[Status]  FROM [dbo].[Account] WHERE [Email] = ? AND [Password] = ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("Role");
                String name = rs.getString("Name");
                int status = rs.getInt("Status");
                String email = id;
                account = new AccountDTO(email, name, role, status);
            }
        }
        return account;
    }

    public AccountDTO getAccountFromID(String id) throws Exception {
        AccountDTO account = null;
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "Select [Name], [Role], [Status] FROM [dbo].[Account] WHERE [Email] = ? ";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("Name");
                    String role = rs.getString("Role");
                    int status = rs.getInt("Status");
                    account = new AccountDTO(id, name, role, status);
                }
            }
        } finally {
            closeConnection();
        }
        return account;
    }

     public boolean registAccount(String email, String name, String password) throws Exception {
        boolean chk = false;
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO [dbo].[Account]([Email],[Name],[Password],[Role],[Status]) VALUES (?,?,?,?,?)";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setInt(4, 2);
                ps.setInt(5, 1);
                chk = ps.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return chk;
    }
    
    public boolean registAvailableAccount(String email, String name, String password) throws Exception {
        boolean chk = false;
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO [dbo].[Account]([Email],[Name],[Password],[Role],[Status]) VALUES (?,?,?,?,?)";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setInt(4, 2);
                ps.setInt(5, 3);
                chk = ps.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return chk;
    }

    public List<AccountDTO> getAccountWithStatus(int status) throws Exception {
        List<AccountDTO> list = new ArrayList<>();
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "SELECT [Email],[Name] FROM [dbo].[Account] WHERE [Status] = ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, status);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    String name = rs.getString("Name");
                    list.add(new AccountDTO(email, name));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean activeUser(String email) throws Exception {
        boolean chk = false;
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "UPDATE [dbo].[Account] SET [Status] = '2' WHERE [Email] = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, email);
                chk = ps.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return chk;
    }
}
