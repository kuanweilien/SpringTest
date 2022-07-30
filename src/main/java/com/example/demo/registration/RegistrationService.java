package com.example.demo.registration;


import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import com.example.demo.email.EmailService;
import com.example.demo.registration.token.ConfirmatinoTokenService;
import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.security.config.WebConfig;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
    
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmatinoTokenService confirmatinoTokenService;
    private final EmailService emailService;
    private final WebConfig webConfig;



    public String register(RegistrationRequest request){

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }

        String token = appUserService.signUpUser(
            new AppUser(request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER)
        );
        
        String link = webConfig.getProtocol() +"://"+webConfig.getHost()+":"+webConfig.getPort()+"/api/v1/registration/confirm?token="+token;
        emailService.send(request.getEmail(), 
                            getEmailContent( 
                                request.getFirstName(),
                                link));
        return token;
    }
    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmToken = confirmatinoTokenService
                        .getToken(token)
                        .orElseThrow(()-> new IllegalStateException("token not found"));

        if(confirmToken.getConfirmeAt() != null){
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmToken.getExpiredAt();
        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }

        confirmatinoTokenService.saveConfirmationToken(confirmToken);
        appUserService.enableAppUser(confirmToken.getAppUser().getEmail(),token);

        return "confirm";
    }
    private String getEmailContent(String name,String link){
        return "Hello " + name + "Please click the link to confirm email : " + link;
    }
}
