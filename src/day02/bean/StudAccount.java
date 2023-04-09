package day02.bean;

import java.io.Serializable;

/**
 * @Author 徐联理
 * @Date 2023/4/8 9:09
 * @Description
 * stud_account实体类
 */
public class StudAccount implements Serializable {
    private Integer sid;
    private String password;

    public StudAccount() {
    }

    public StudAccount(Integer sid, String password) {
        this.sid = sid;
        this.password = password;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
