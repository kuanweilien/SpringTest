package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<String> getHelloWorld(){
        return List.of("Hello","Spring","Boot");
    }
    @Transactional
    public void setUserName(Long id,String name) 
    {
        UserModel userModel = userRepository.findById(id).orElseThrow();

        if(name != null){
            userModel.setUserName(name);
        }
    }
    /*
    ArrayList<UserModel> listAll();
    UserModel getById(Long id);
    UserModel saveOrUpdate(UserModel user);
    void delete(Long id);
    */
}
