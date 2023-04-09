package day02.teaching;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author titan-zou
 * @Date 2023/4/7 14:36
 * @Description
 */
public class DruidPoolDemo {
    public static void main(String[] args) throws Exception {
        //通过德鲁伊连接池获取连接对象
        Properties p = new Properties();
        //加载配置文件
        p.load(DruidPoolDemo.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        DataSource dataSource =  DruidDataSourceFactory.createDataSource(p);

        Connection con = dataSource.getConnection();

        String sql="select * from users";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            System.out.println("name="+rs.getString("u_name"));
        }

    }
}
