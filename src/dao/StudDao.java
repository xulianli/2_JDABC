package dao;

import day02.bean.StudAccount;
import day02.bean.StudInfo;
import day02.homework.Interfaces;
import util.DruidUtil;

import java.sql.*;
import java.util.Scanner;

/**
 * @Author 徐联理
 * @Date 2023/4/8 10:10
 * @Description
 */
public class StudDao {
    Connection con = DruidUtil.getConn();
    Interfaces in = new Interfaces();
    Scanner sc = new Scanner(System.in);
    //注册
    public void register() throws SQLException {
        System.out.println("请输入学号:");
        Integer id = sc.nextInt();
        System.out.println("请输入密码:");
        String pwd = sc.next();
        System.out.println("请输入姓名:");
        String name = sc.next();
        System.out.println("请输入年龄:");
        Integer age = sc.nextInt();
        System.out.println("请输入性别:");
        String sex = sc.next();
        System.out.println("请输入班级:");
        String cls = sc.next();
        System.out.println("请输入院系:");
        String dept = sc.next();
        StudAccount studAccount = new StudAccount(id,pwd);
        StudInfo studInfo = new StudInfo(id,name,age,sex,cls,dept);
        String sql1 = "insert into stud_account(stu_id,password) values(?,?)";
        String sql2 = "insert into stud_info(id, name, age, sex, class, dept) values(?,?,?,?,?,?)";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps1.setInt(1,studAccount.getSid());
        ps1.setString(2,studAccount.getPassword());
        PreparedStatement ps2 = con.prepareStatement(sql2);
        ps2.setInt(1,studInfo.getSid());
        ps2.setString(2, studInfo.getSname());
        ps2.setInt(3, studInfo.getSage());
        ps2.setString(4, studInfo.getSsex());
        ps2.setString(5, studInfo.getSclass());
        ps2.setString(6, studInfo.getSdept());
        int p2 = ps2.executeUpdate();
        int p1 = ps1.executeUpdate();
        int result = p2+p1;
        if (result>0){
            System.out.println("当前学员注册成功!");
        }else{
            System.out.println("注册失败");
        }
    }
    //登录
    public void login() throws SQLException {
        System.out.println("请输入学号:");
        Integer id = sc.nextInt();
        System.out.println("请输入密码:");
        String pwd = sc.next();
        String sql1 ="select * from stud_account where stu_id=? and password=?";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps1.setInt(1,id);
        ps1.setString(2, pwd);
        ResultSet rs1 = ps1.executeQuery();
        String sql2 ="select * from stud_info where id=?";
        PreparedStatement ps2 = con.prepareStatement(sql2);
        ps2.setInt(1,id);
        ResultSet rs2 = ps2.executeQuery();
        StudAccount si = null;
        if(rs1.next() && rs2.next()){
            si = new StudAccount(id,pwd);
            in.loginInterface(rs2);
        }else{
            System.out.println("学员信息输入有误，请重新输入：");
            login();
        }
        String option = sc.next();
        if ("1".equals(option)){
            showInfo(si);
        }else if ("2".equals(option)){
            updateInfo(si);
        }else if ("3".equals(option)){
            System.exit(0);
        }
    }

    private void showInfo(StudAccount studAccount) throws SQLException {
        String sql = "select * from stud_info where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, studAccount.getSid());
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            System.out.println("1.学号："+rs.getInt("id")+"\n" +
                    "2.姓名："+rs.getString("name")+"\n" +
                    "3.年龄："+rs.getInt("age")+"\n" +
                    "4.性别："+rs.getString("sex")+"\n" +
                    "5.班级："+rs.getString("class")+"\n" +
                    "6.院系："+rs.getString("dept"));

        }
    }

    private void updateInfo(StudAccount studAccount) throws SQLException {
        showInfo(studAccount);
        System.out.println("1、修改姓名 2、修改年龄 3、修改性别 4、修改班级 5、修改院系");
        System.out.println("请输入需要修改的序号：");
        String option = sc.next();
        switch (option){
            case "1":
                System.out.println("请输入新的姓名：");
                String newName = sc.next();
                String sql1 = "update stud_info set name='"+newName+"' where id='"+studAccount.getSid()+"'";
                commit(sql1, studAccount);
                break;
            case "2":
                System.out.println("请输入新的年龄：");
                Integer newAge = sc.nextInt();
                String sql2 = "update stud_info set age='"+newAge+"' where id='"+studAccount.getSid()+"'";
                commit(sql2, studAccount);
                break;
            case "3":
                System.out.println("请输入新的性别：");
                String newSex = sc.next();
                String sql3 = "update stud_info set sex='"+newSex+"' where id='"+studAccount.getSid()+"'";
                commit(sql3, studAccount);
                break;
            case "4":
                System.out.println("请输入新的班级：");
                String newCls = sc.next();
                String sql4 = "update stud_info set class='"+newCls+"' where id='"+studAccount.getSid()+"'";
                commit(sql4, studAccount);
                break;
            case "5":
                System.out.println("请输入新的院系：");
                String newDept = sc.next();
                String sql5 = "update stud_info set dept='"+newDept+"' where id='"+studAccount.getSid()+"'";
                commit(sql5,studAccount);
                break;
            default:
                System.out.println("输入指令有误");
                break;
        }

    }

    private void commit(String sql, StudAccount studAccount) throws SQLException {
        System.out.println("1、提交  2、继续选择");
        if ("1".equals(sc.next())){
            Statement stm = con.createStatement();
            if(stm.executeUpdate(sql)>0){
                System.out.println("学员信息更新成功！");
                System.out.println("1、返回主页面  2、退出系统");
                if ("1".equals(sc.next())){
                    in.primeInterface();
                }else if ("2".equals(sc.next())){
                    System.exit(0);
                }
            }else {
                System.out.println("操作失败");
            }
        }else if ("2".equals(sc.next())){
            updateInfo(studAccount);
        }
    }


}
