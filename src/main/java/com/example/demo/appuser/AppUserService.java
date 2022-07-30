package com.example.demo.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.registration.token.ConfirmationRepository;
import com.example.demo.registration.token.ConfirmationToken;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationRepository confirmationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException(String.format( USER_NOT_FOUND_MSG, username)));
    }
    public String signUpUser(AppUser appUser){

        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if(userExists){
            throw new IllegalStateException("email already exists");
        }

        // TODO : Save User
        String encodePassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodePassword);

        appUserRepository.save(appUser);

        // TODO : Set Token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            null,
            appUser
        );
        confirmationRepository.save(confirmToken);

        // TODO : Send Email
        


        return token;
    }
    public void enableAppUser(String email,String token){
        AppUser user = appUserRepository.findByEmail(email)
                            .orElseThrow(()->new IllegalStateException("email not found"));

        user.setEnabled(true);

        ConfirmationToken confirmToken = confirmationRepository.findByToken(token)
                            .orElseThrow(()-> new IllegalStateException("toekn not found"));
        confirmToken.setConfirmeAt(LocalDateTime.now());

    }
}
