package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author 徐联理
 * @Date 2023/4/7 20:34
 * @Description
 * 连接池
 */
public class DruidUtil {
    private static DataSource dataSource;

    static {
        Properties p = new Properties();
        try {
            p.load(DruidUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            dataSource =  DruidDataSourceFactory.createDataSource(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConn() {
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) throws SQLException {
        String sql="select * from users";
        Connection con = DruidUtil.getConn();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            System.out.println("name="+rs.getString("account"));
        }
    }
}
