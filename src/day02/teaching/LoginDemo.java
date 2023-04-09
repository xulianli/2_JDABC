package day02.teaching;

import util.DBUtil;

import java.sql.*;
import java.util.Scanner;

/**
 * @Author titan-zou
 * @Date 2023/4/7 9:40
 * @Description
 */
public class LoginDemo {

    public static void main(String[] args) {
        //通过JDBC实现登录的操作行为
        Connection con = DBUtil.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String name = sc.next();
        System.out.println("请输入密码:");
        String pwd = sc.next();

//        String sql = "select * from users where u_name='"+name+"' and u_password='"+pwd+"'";
        //SQL语句编译对象，数据会通过拼接方式填充到SQL语句，然后才去执行SQL语句所以很容易出现SQL注入攻击
//        Statement stm = null;
        //为了解决SQL注入攻击，JDK中诞生了一种在添加数据之前确定SQL语句结构的一种SQL预编译语句对象
        PreparedStatement ps =null;
        String sql ="select * from users where u_name=? and u_password=?";

        ResultSet rs = null;
        try {
            //使用Statement方式
//            stm = con.createStatement();
//            rs = stm.executeQuery(sql);

            //使用预编译SQL语句对象方式
            ps = con.prepareStatement(sql);
            //通过预编译SQL语句对象对占位符进行赋值 setXXX(参数1,参数2); 参数1占位符的位置 参数2 数据
            ps.setString(2,pwd);
            ps.setString(1,name);

            rs = ps.executeQuery();
            //登录操作最终获取到结果集中的数据只会存在一条记录，所以直接进行判断
            if(rs.next()){
                System.out.println("欢迎"+rs.getString("u_name")+"来到动力节点官方系统");
            }else{
                System.out.println("用户信息有误...");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeAll(rs);
            DBUtil.closeAll(ps);
            DBUtil.closeAll(con);
        }


    }

}
