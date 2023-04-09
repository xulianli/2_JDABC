package day02.teaching;

import day02.bean.Users;
import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author titan-zou
 * @Date 2023/4/7 9:07
 * @Description
 */
public class SelectDemo2 {
    public static void main(String[] args) {
        Connection con = DBUtil.getConnection();
        String sql = "select * from users";
        Statement stm = null;
        ResultSet rs = null;
        //定义一个存放从结果集中获取到的每个用户对象的存储集合
        List<Users> list = new ArrayList<>();  //在list集合中ArrayList适合查询、修改操作
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                //在循环遍历结果集的时候每个用户数据必须用一个用户对象进行存储
                Users u = new Users();
                //通过结果集对象提供的getXXX方法对用户对象中对应的字段进行数据存储
                u.setUid(rs.getInt("u_id"));
                u.setUname(rs.getString("u_name"));
                u.setUpassword(rs.getString("u_password"));
                list.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeAll(rs);
            DBUtil.closeAll(stm);
            DBUtil.closeAll(con);
        }

        //循环遍历集合中存储的用户对象
        for(Users us : list ){
            System.out.println(us.getUname());
        }


    }
}
