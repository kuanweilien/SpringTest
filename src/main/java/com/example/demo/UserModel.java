package com.example.demo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class UserModel{
    @Id
    @GeneratedValue
    private Long _id;
    private String user_name;
    private Integer sex;
    private Date birthday;

    public UserModel(){}

    public UserModel(Long Id,
                    String user_name,
                    Integer sex,
                    Date birthday){
        this._id = Id;
        this.user_name = user_name;
        this.sex = sex;
        this.birthday = birthday;
    }

    public Long getId(){
        return _id;
    }
    @Column(name = "user_name", nullable = false)
    public String getUser_name(){
        return user_name;
    }
    @Column(name = "sex")
    public Integer getSex(){
        return sex;
    }
    @Column(name = "birthday")
    public Date getBirthday(){
        return birthday;
    }

    public void setId(Long Id){
        this._id = Id;
    }
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }
    public void setSex(Integer sex){
        this.sex = sex;
    }
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

}