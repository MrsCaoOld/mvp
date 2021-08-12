package com.bw.Caohaigang20210806.food;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Food {
    @Id(autoincrement = true)
    private Long id;
    private String pic;
    private String title;
    private int num;
    @Generated(hash = 920299904)
    public Food(Long id, String pic, String title, int num, String check) {
        this.id = id;
        this.pic = pic;
        this.title = title;
        this.num = num;
        this.check = check;
    }
    @Generated(hash = 866324199)
    public Food() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    private String check = "";

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
