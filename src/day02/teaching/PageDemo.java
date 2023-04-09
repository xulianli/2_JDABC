package day02.teaching;

import day02.bean.Users;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author titan-zou
 * @Date 2023/4/7 10:03
 * @Description
 * 分页查询
 */
public class PageDemo {
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

    //定义一个获取总记录数的方法
    public int getAll() throws SQLException {
        String sql = "select count(*) num from users";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("num");
        }
        return 0;
    }

    //定义一个获取分页数据的方法
    public List<Users> pages(int pageIndex,int pageSize) throws SQLException {
        List<Users> list =new ArrayList<>();
        String sql ="select * from users limit ?,?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,(pageIndex-1)*pageSize);
        ps.setInt(2,pageSize);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Users u = new Users();
            u.setUid(rs.getInt("u_id"));
            u.setUname(rs.getString("u_name"));
            u.setUpassword(rs.getString("u_password"));
            list.add(u);
        }
        return list;
    }

    public static void main(String[] args) {
        PageDemo pd = new PageDemo();
        try {
            //获取总记录数
            int sum = pd.getAll();
            //获取每页显示数量
            int page = 3;
            //获取总页数
            int pageSum = sum%page==0? sum/page:sum/page+1;

            System.out.println("当前总记录数:"+sum);
            System.out.println("当前总页数:"+pageSum);
            System.out.println("输出结果:");
            List<Users> list = pd.pages(1,page);
            for(Users u: list){
                System.out.println("name="+u.getUname());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
