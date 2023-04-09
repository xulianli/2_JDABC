package day01.teaching;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @Author titan-zou
 * @Date 2023/4/6 17:03
 * @Description
 */
public class UpdateDemo {
    public static void main(String[] args) {
        /**
         * 修改数据库表中的指定数据
         */
        Connection con = DBUtil.getConnection();
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入用户名称:");
        String name = sc.next();
        System.out.println("请输入需要修改的密码");
        String pwd = sc.next();

        String sql = "update users set u_password='"+pwd+"' where u_name='"+name+"'";
        Statement stm = null;
        try {
            stm = con.createStatement();
            if(stm.executeUpdate(sql)>0){
                System.out.println(name+"用户密码修改成功");
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
