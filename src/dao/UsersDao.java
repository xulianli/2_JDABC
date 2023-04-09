package dao;

import day02.bean.Users;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author titan-zou
 * @Date 2023/4/7 15:09
 * @Description
 * 用户模块的所有数据库操作功能
 */
public class UsersDao {

    //定义数据库连接对象属性
    Connection con = DBUtil.getConnection();

    //注册
    public int save(Users users) throws SQLException {
        String sql = "insert into users(u_name,u_password) values(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,users.getUname());
        ps.setString(2,users.getUpassword());
        return ps.executeUpdate();
    }

    //登录
    public Users findByName(Users users) throws SQLException {
        String sql="select * from users where u_name=? and u_password=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,users.getUname());
        ps.setString(2,users.getUpassword());
        ResultSet rs = ps.executeQuery();
        Users u = null;
        if(rs.next()){
            u=new Users();
            u.setUid(rs.getInt("u_id"));
            u.setUname(rs.getString("u_name"));
            u.setUpassword(rs.getString("u_password"));
        }
        return u;
    }


    //密码找回

    //地址修改


}
