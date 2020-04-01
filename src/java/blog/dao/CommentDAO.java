/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.dto.CommentDTO;
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
public class CommentDAO {

    Connection cnn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

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

    public List<CommentDTO> getCommentFromPost(int postID) throws Exception {
        List<CommentDTO> list = new ArrayList<>();
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "SELECT [CommentContent],[AccountID] FROM [dbo].[Comment] WHERE [PostID] = ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, postID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString("CommentContent");
                    String id = rs.getString("AccountID");
                    list.add(new CommentDTO(content, id));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean createComment(int pID, String aID, String content) throws Exception {
        boolean chk = false;
        try {
            cnn = blog.db.DBUtil.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO [dbo].[Comment]([PostID],[AccountID],[CommentContent]) values (?, ?, ?)";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, pID);
                ps.setString(2, aID);
                ps.setString(3, content);
                chk = ps.executeUpdate() > 0;
            }
        }finally{
            closeConnection();
        }
        return chk;
                }

}
