package day02.homework;

import dao.StudDao;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @Author 徐联理
 * @Date 2023/4/8 9:53
 * @Description
 */
public class MIS {
    public static void main(String[] args) {
        StudDao sd = new StudDao();
        Interfaces in = new Interfaces();
        in.primeInterface();
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        try{
            if ("1".equals(input)){
                sd.login();
            } else if ("2".equals(input)){
                in.registerInterface();
                sd.register();
                System.out.println("1、继续注册  2、进入登录 3、退出系统!");
                String option = sc.next();
                try{
                    if ("1".equals(option)){
                        sd.register();
                    }else if ("2".equals(option)){
                        sd.login();
                    }else if ("3".equals(option)){
                        System.exit(0);
                    }else {
                        throw new IllegalArgumentException();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
