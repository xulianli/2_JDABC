package day02.teaching;

import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author titan-zou
 * @Date 2023/4/7 8:55
 * @Description
 */
public class SelectDemo1 {
    public static void main(String[] args) {
        /**
         * 查询数据库数据
         */
        //获取数据库连接对象
        Connection con = DBUtil.getConnection();
        //定义查询SQL语句
        String sql = "select * from users";
        //定义SQL语句对象
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = con.createStatement();
            //执行查询操作
            rs = stm.executeQuery(sql);
            //通过获取到的结果集对象进行数据遍历
            while(rs.next()){
                //getXXX()  通过结果接从指定字段中读取对应类型的数据
                System.out.println("用户名:"+rs.getString("u_name")+",密码:"+rs.getString("u_password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeAll(rs);
            DBUtil.closeAll(stm);
            DBUtil.closeAll(con);
        }


    }
}
