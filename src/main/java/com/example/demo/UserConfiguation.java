package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class UserConfiguation {
    @Autowired
    UserRepository userRepository;

    @Bean
    CommandLineRunner commandLineRunner(){
		return args->{
            List<UserModel> users = new ArrayList<>();
            users.add(new UserModel("Ben",1,LocalDate.of(1993,5,14)));
            users.add(new UserModel("Sandy",0,LocalDate.of(1992,3,15)));
			
			userRepository.saveAll(users);
		};
	}
}
