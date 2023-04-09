package day01.teaching;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author titan-zou
 * @Date 2023/4/6 16:48
 * @Description
 */
public class InsertDemo {
    public static void main(String[] args) {
        /**
         * 添加数据到数据库的指定表中
         */
        //获取数据库连接对象
        Connection con = DBUtil.getConnection();
        Statement stm=null;
        //定义SQL语句
        String sql = "insert into users(u_name,u_password) values('demo','123')";
        try {
            //创建SQL语句编译对象
            stm = con.createStatement();
            //执行SQL语句
            int i =stm.executeUpdate(sql);
            if(i>0){
                System.out.println("插入成功...");
            }else{
                System.out.println("操作失败...");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //在关闭资源中遵循的是先开后关的原则
            DBUtil.closeAll(stm);
            DBUtil.closeAll(con);
        }


    }
}
