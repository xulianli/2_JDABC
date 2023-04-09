package day01.homework;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @Author 徐联理
 * @Date 2023/4/6 20:06
 * @Description
 */
public class AddressDemo {
    public static void main(String[] args) {
        Connection con = DBUtil.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入地址（空格分开）：");
        String[] address = sc.nextLine().split(" ");
        String province= address[0];
        String city =  address[1];
        String district = address[2];
        String street = address[3];
        String detail = address[4];
        Integer zip = Integer.parseInt(address[5]);
        Integer userid = Integer.parseInt(address[6]);
        String sql = "Insert into address(add_ID, Province, City, District, Street," +
                "Detail, zip, user_id) values('2','"+province+"','"+city+"','"+district
                +"','"+street+"','"+detail+"','"+zip+"','"+userid+"')";
        Statement stm = null;
        try {
            stm = con.createStatement();
            if (stm.executeUpdate(sql)>0){
                System.out.println("Address Insert Success");
            } else {
                System.out.println("Address Insert Failed");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeAll(stm);
            DBUtil.closeAll(con);
        }

    }
}
