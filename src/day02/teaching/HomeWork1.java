package day2.homework;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author titan-zou
 * @Date 2023/4/7 8:40
 * @Description
 */
public class HomeWork1 {
    public static void main(String[] args) {
        Connection con = DBUtil.getConnection();
        String sql ="insert into address(add_name,add_phone,add_content,u_id) values('多多老师','134444444','岛国',1)";
        Statement stm = null;
        try {
            stm = con.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
