package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<UserModel, Long>{ 
    //@Query("select * from tb_user where user_name = ")
    Optional<UserModel> findByUserName(String userName);
}
