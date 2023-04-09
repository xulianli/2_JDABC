package day01.practice;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @Author 徐联理
 * @Date 2023/4/6 17:11
 * @Description
 */
public class deleteSQL {
    public static void main(String[] args) {
        Connection con = DBUtil.getConnection();
        Scanner scanner = new  Scanner(System.in);
        System.out.println("请输入删除的用户名：");
        String name = scanner.next();
        Statement stm = null;
        String sql = "delete from users where user_account = '"+name+"'";
        try {
            stm = con.createStatement();
            if (stm.executeUpdate(sql)>0){
                System.out.println("delete success");
            } else{
                System.out.println("delete failure");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            DBUtil.closeAll(stm);
            DBUtil.closeAll(con);
        }
    }
}
