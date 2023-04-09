package day02.bean;

import java.io.Serializable;

/**
 * @Author titan-zou
 * @Date 2023/4/7 8:53
 * @Description
 */
//用户实体类
public class Users implements Serializable {

    private Integer uid;
    private String uname;
    private String upassword;

    public Users() {
    }

    public Users(Integer uid, String uname, String upassword) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }
}
