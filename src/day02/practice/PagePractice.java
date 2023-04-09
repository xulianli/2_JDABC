package day02.practice;

import day02.bean.Users;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 徐联理
 * @Date 2023/4/7 10:45
 * @Description
 * 分页查询
 */
public class PagePractice {

    /***
     * 分页业务分析
     * @param args
     * 目的：在一个显示区域中由于数据库返回结果集的数据过多导致显示存在无法有效区域的展示，所以将
     * 多条记录拆分成多个页面显示。
     * 业务分析：
     *      1、定义每次显示的记录条数 --页面显示数量
     *      2、获取总记录数
     *      3、获取总页数 --> 总记录数/每页显示数量
     */
    //在当前类中定义数据库连接对象的属性
    Connection con = DBUtil.getConnection();

    //定义一个获取总记录数的方法, 返回一个整数
    public int getAll() throws SQLException {
        String sql = "select count(*) num from users";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return rs.getInt("num");
        }
        return 0;
    }

    //定义一个获取分页数据的方法，将每页的数据放入List中
    public List<Users> getPage(int pageIndex, int pageSize) throws SQLException {
        //创建Users集合存放数据
        List<Users> list = new ArrayList<>();
        //创建带有占位符SQL语句
        String sql = "select * from users limit ?,?";
        //创建preparestatement语句用来执行SQL语句
        PreparedStatement ps = con.prepareStatement(sql);
        //传入第一个占位符的数据：从第几条数据开始，首条数据是0
        ps.setInt(1,(pageIndex-1)*pageSize);
        //传入第二个占位符的数据：显示几条数据，就是页面显示条数
        ps.setInt(2, pageSize);
        //获取执行preparestatement后的结果集
        ResultSet rs = ps.executeQuery();
        //处理结果集
        while (rs.next()){
            Users u = new Users(rs.getInt("uid"),rs.getString("uname"),
                    rs.getString("upassword"));
            list.add(u);
        }
        return list;
        //每条数据创建数据对象
    }
    public static void main(String[] args){
        PagePractice pp = new PagePractice();
        try {
            int sum = pp.getAll();
            int page =3;
            int pageSum = sum%page==0? sum/page:sum/page+1;
            System.out.println("总记录数："+sum);
            System.out.println("总页数："+pageSum);
            System.out.println("当前页面显示：");
            List<Users> list = pp.getPage(3,page);
            for (Users u: list) {
                System.out.println("account:"+u.getUname());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
