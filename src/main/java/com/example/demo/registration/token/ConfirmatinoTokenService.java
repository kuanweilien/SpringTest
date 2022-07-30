package com.example.demo.registration.token;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmatinoTokenService {
    ConfirmationRepository confirmationRepository;
    
    public void saveConfirmationToken(ConfirmationToken token){
        confirmationRepository.save(token);
    }
    public Optional<ConfirmationToken> getToken(String token){
        return confirmationRepository.findByToken(token);
    }
    
}
