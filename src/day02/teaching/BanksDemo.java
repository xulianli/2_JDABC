package day02.teaching;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author titan-zou
 * @Date 2023/4/7 14:04
 * @Description
 */
public class BanksDemo {
    public static void main(String[] args) throws SQLException {
        //获取数据库连接对象
        Connection con = DBUtil.getConnection();
        //定义SQL语句
        String sql1 = "update bank set b_money=b_money+100 where b_name='罗文健'";
        String sql2 = "update bank set b_money=b_money-100 where b_name='姜权'";
        Statement stm = null;
        try {
            //设置数据库事务提交方式为手动
            con.setAutoCommit(false);
            //定义SQL语句对象
            stm = con.createStatement();
            //执行SQL语句
            stm.executeUpdate(sql1);
            stm.executeUpdate(sql2);

        } catch (SQLException throwables) {
            //处理来自数据的异常操作
            try {
                con.rollback();
                System.out.println("转账操作失败....");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            con.commit();
//            System.out.println("转账成功...");
            DBUtil.closeAll(stm);
            DBUtil.closeAll(con);
        }


    }

}
