package spring_security_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import spring_security_basic.entity.User;
import spring_security_basic.repo.UserRepo;

@Component
public class UserProvider implements AuthenticationProvider {
    @Autowired
    private UserRepo userRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String  username =(String)authentication.getPrincipal();
        String  password = authentication.getCredentials().toString();
      User user=  userRepo.findByUsername(username);

      if(user.getUsername().equals(username)){
          return new UsernamePasswordAuthenticationToken(username, password, authentication.getAuthorities());
      }
        else {
            throw new BadCredentialsException("invalid user");
      }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
