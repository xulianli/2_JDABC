package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author 徐联理
 * @Date 2023/4/6 16:12
 * @Description
 */
public final class DBUtil {

    private DBUtil(){}

    private static String driver;
    private static String url;
    private static String username;
    private static String password;


    static{
        Properties pro = new Properties();
        try {
            pro.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            driver = pro.getProperty("driverClassName");
            url = pro.getProperty("url");
            username = pro.getProperty("username");
            password = pro.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
            System.out.println("database connection success");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeAll(AutoCloseable autoCloseable){
        try {
            autoCloseable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBUtil.getConnection();
    }
}
