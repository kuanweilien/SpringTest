package com.example.demo;

import java.util.ArrayList;

public interface UserService {
    ArrayList<UserModel> listAll();
    UserModel getById(Long id);
    UserModel saveOrUpdate(UserModel user);
    void delete(Long id);
}
