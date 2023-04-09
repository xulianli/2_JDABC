package day02.bean;

import java.io.Serializable;

/**
 * @Author 徐联理
 * @Date 2023/4/8 9:18
 * @Description
 * stud_info实体类
 */
public class StudInfo implements Serializable {
    private Integer sid;
    private String sname;
    private Integer sage;
    private String ssex;
    private String sclass;
    private String sdept;

    public StudInfo() {
    }

    public StudInfo(Integer sid, String sname, Integer sage, String ssex, String sclass, String sdept) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.ssex = ssex;
        this.sclass = sclass;
        this.sdept = sdept;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }
}
