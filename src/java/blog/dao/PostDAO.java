/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.dto.PostDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tan
 */
public class PostDAO {
    
    Connection cnn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    AccountDAO accountDAO = new AccountDAO();
    CommentDAO commentDAO = new CommentDAO();
    
    public void closeConnection() throws SQLException {
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
    
    public int getTotalPost(int status) throws Exception {
        int total = 0;
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "SELECT COUNT([ID]) as Total FROM [dbo].[Post] WHERE Status = ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, status);
                rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt("Total");
                }
            }
        } finally {
            closeConnection();
        }
        return total;
    }
    
    public int getTotalSearchPost(int status, String search) throws Exception {
        int total = 0;
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "SELECT COUNT([ID]) as Total FROM [dbo].[Post] WHERE Status = ? AND [Title] LIKE ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, status);
                ps.setString(2, "%"+search+"%");
                rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt("Total");
                }
            }
        } finally {
            closeConnection();
        }
        return total;
    }
    
    public List<PostDTO> getAllPostWithStatus(int status, int skip) throws Exception {
        List<PostDTO> list = new ArrayList<>();
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                
                String sql = "SELECT [ID], [Title], [Date], [ShortDescription],[AccountID], [PostContent] From [dbo].[Post] WHERE [Status] = ? ORDER BY [Date] DESC OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, status);
                ps.setInt(2, skip);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String title = rs.getString("Title");
                    Date date = rs.getTimestamp("Date");
                    String shortDescrip = rs.getString("ShortDescription");
                    String accountID = rs.getString("AccountID");
                    list.add(new PostDTO(id, title, shortDescrip, date, accountDAO.getAccountFromID(accountID)));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public List<PostDTO> searchPostByTitleWithStatus(String searchContent, int status, int skip) throws Exception {
        List<PostDTO> list = new ArrayList<>();
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "SELECT [ID], [Title], [Date], [ShortDescription],[AccountID] FROM [dbo].[Post] WHERE [Status] = ? AND [Title] LIKE ? ORDER BY [Date] DESC OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
                ps = cnn.prepareStatement(sql);
                ps.setString(2, "%" + searchContent + "%");
                ps.setInt(1, status);
                ps.setInt(3, skip);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String title = rs.getString("Title");
                    Date date = rs.getTimestamp("Date");
                    String shortDescription = rs.getString("ShortDescription");
                    String accountID = rs.getString("AccountID");
                    list.add(new PostDTO(id, title, shortDescription, date, accountDAO.getAccountFromID(accountID)));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public PostDTO showPostDetail(int id) throws Exception {
        PostDTO post = null;
        
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "SELECT [AccountID], [Title], [PostContent],[Status], [ShortDescription], [Date] FROM [dbo].[Post] WHERE [ID] = ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String aID = rs.getString("AccountID");
                    String title = rs.getString("Title");
                    String content = rs.getString("PostContent");
                    int status = rs.getInt("Status");
                    String shortDescrip = rs.getString("ShortDescription");
                    Date date = rs.getTimestamp("Date");
                    post = new PostDTO(id, status, title, content, shortDescrip, date, accountDAO.getAccountFromID(aID), commentDAO.getCommentFromPost(id));
                }
            }
            
        } finally {
            closeConnection();
        }
        return post;
    }
    
    public boolean createPost(String email, String title, String shortDescript, String content, Date date) throws Exception {
        boolean chk = false;
        try {
            Timestamp ts = new Timestamp(date.getTime());
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO [dbo].[Post]([AccountID],[Title],[Date],[PostContent],[ShortDescription],[Status]) VALUES (?,?,?,?,?,?)";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, title);
                ps.setTimestamp(3, ts);
                ps.setString(4, content);
                ps.setString(5, shortDescript);
                ps.setInt(6, 1);
                chk = ps.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return chk;
    }
    
    public boolean setPostStatus(int id, int status) throws Exception {
        boolean chk = false;
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "UPDATE [dbo].[Post] SET [Status] = ? WHERE [ID] = ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, status);
                ps.setInt(2, id);
                chk = ps.executeUpdate() > 0;
            }
            
        } finally {
            closeConnection();
        }
        return chk;
    }
}
