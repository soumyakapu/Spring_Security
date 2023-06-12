package com.spring_security_oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@SpringBootApplication
@RestController
public class SpringSecurityOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauthApplication.class, args);
    }
    @GetMapping("/")
    public String get(){
        return "accessed by everyone";
    }
    @GetMapping("/valid")
    public String valid(){
      var username =  SecurityContextHolder.getContext().getAuthentication();
        return "Hello" +getName(username);
    }

    private String getName(Authentication username) {
        return Optional.of(username.getPrincipal())
                .filter(OidcUser.class::isInstance)
                .map(OidcUser.class::cast)
                .map(OidcUser::getGivenName)
                .orElseGet(username::getName);

    }
}
