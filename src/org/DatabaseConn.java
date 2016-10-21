package org;


import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 54333 on 2016/10/11.
 */
public class DatabaseConn {
    private static Connection conn;
    public static Connection getConnection() throws
            SQLException,NamingException{
        try {
            //String url = "jdbc:mysql://localhost:3306/bookdb";
            String url = "jdbc:mysql://yatmulgizirl.rds.sae.sina.com.cn:10673/bookdb";
            String username = "root";
            String password = "wjsw5945@";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功！");
        }catch(Exception e){
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args){
        DatabaseConn dbc = new DatabaseConn();
        try{
            dbc.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
