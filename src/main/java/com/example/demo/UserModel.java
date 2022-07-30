package com.example.demo;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.*;

@Entity
@Table(name = "tb_user")
public class UserModel{
    @Id
    @SequenceGenerator(
        name = "tb_user_seq",
        sequenceName = "tb_user_seq",
        allocationSize = 100
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "tb_user_seq"
    )
    @Column(
        name = "id",
        updatable = false
    )
    private Long id;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "sex")
    private Integer sex;
    @Transient
    private Integer age;
    @Column(name = "birthday")
    private LocalDate birthday;

    public UserModel(){}

    public UserModel(
                    String userName,
                    Integer sex,
                    LocalDate birthday){
        this.userName = userName;
        this.sex = sex;
        this.birthday = birthday;
    }

    public Long getId(){
        return id;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public Integer getSex(){
        return sex;
    }
    public Integer getAge(){
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }
    public LocalDate getBirthday(){
        return birthday;
    }

    public void setId(Long Id){
        this.id = Id;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setSex(Integer sex){
        this.sex = sex;
    }
    public void setBirthday(LocalDate birthday){
        this.birthday = birthday;
    }

}