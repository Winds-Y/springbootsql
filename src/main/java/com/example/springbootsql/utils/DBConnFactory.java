package com.example.springbootsql.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class DBConnFactory {
    private static final String TAG="DBConnFactory";
    private static Connection con=null;
    private static PreparedStatement stmt=null;
    private static ResultSet rs=null;
    private static String sql;
    public static void initDBconn () {
        try {
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            String userName = "root";
            String url = "jdbc:mysql://localhost:3306/exercise?useSSL=false&&serverTimezone=UTC";
            String password = "123456";
            con=(Connection) DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public static void setSql(String sql) throws SQLException {
        DBConnFactory.sql=sql;
        stmt=(PreparedStatement) con.prepareStatement(sql);
    }
    public static void cbsetString(int index,String value) throws SQLException {
       stmt.setString(index,value);
    }
    public static void cbsetInt(int index,int value) throws SQLException {
       stmt.setInt(index,value);
    }
    public static void cbsetBinaryStream(int index, FileInputStream in) throws IOException, SQLException {
       //stmt.setBinaryStream(index,in,in.available());
       stmt.setBlob(index,in,in.available());
    }
    public static ResultSet executeQuery() {
        try {
            rs=stmt.executeQuery();
        }catch (SQLException e) {
            // TODO: handle exception
            System.out.println("DBConnFactory.executeQuery()");
            System.err.println("executeQuery发生异常："+e.getMessage());
            System.err.println("异常SQL语句："+sql);
        }
        return rs;
    }
    public static void executeUpdate() {
        int rowCount;
        try {
            rowCount=stmt.executeUpdate();
            System.out.println(TAG+"->executeUpdate->rowCount="+rowCount);
        }catch (SQLException e) {
            // TODO: handle exception
            System.out.println("DBConnFactory.executeUpdate()");
            System.err.println("executeUpdate发生异常："+e.getMessage());
            System.err.println("异常SQL语句："+sql);
        }
    }
    public static void close() {
        try {
            if(rs!=null)rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
