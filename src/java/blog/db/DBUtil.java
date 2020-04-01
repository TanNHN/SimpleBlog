/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tan
 */
public class DBUtil {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection cnn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SimpleBlog";
        cnn = DriverManager.getConnection(url, "sa", "1");
        return cnn;
    }
}
