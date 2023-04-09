package day01.teaching;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @Author titan-zou
 * @Date 2023/4/6 16:58
 * @Description
 */
public class InsertDemo2 {
    public static void main(String[] args) {
        //获取数据库连接对象
        Connection con = DBUtil.getConnection();
        //定义控制台输入工具
        Scanner scanner = new Scanner(System.in);
        //从控制台获取输入数据
        System.out.println("请输入用户名:");
        String name = scanner.next();
        System.out.println("请输入密码:");
        String pwd = scanner.next();

        String sql = "insert into users(u_name,u_password) values('"+name+"','"+pwd+"')";
        Statement stm = null;
        try {
            stm = con.createStatement();
            if(stm.executeUpdate(sql)>0){
                System.out.println("插入成功");
            }else {
                System.out.println("操作失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeAll(stm);
            DBUtil.closeAll(con);
        }


    }
}
