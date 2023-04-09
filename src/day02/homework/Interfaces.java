package day02.homework;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author 徐联理
 * @Date 2023/4/8 15:50
 * @Description
 */
public class Interfaces {
    public void primeInterface(){
        System.out.println("*********************************");
        System.out.println("   欢迎进行动力节点学员管理系统");
        System.out.println("\t1、登录\t\t\t2、注册");
        System.out.println("*********************************");
    }

    public void loginInterface(ResultSet rs) throws SQLException {
        System.out.println("*********************************\n" +
                "\t\t欢迎"+rs.getString("name")+"进入管理系统\n" +
                "*********************************\n"+
                "1、查看学员信息  2、修改学员信息 3、退出系统");
    }

    public void registerInterface(){
        System.out.println("*********************************\n" +
                "\t\t注册学员管理系统\n" +
                "*********************************");
    }

}
