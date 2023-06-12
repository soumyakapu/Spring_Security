package spring_security_basic;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spring_security_basic.entity.User;
import spring_security_basic.repo.UserRepo;

@SpringBootApplication
@RestController
public class SpringSecurityBasicApplication {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityBasicApplication.class, args);
    }
    @GetMapping("/")
    public String get(){
        return  "access for everyOne";
    }
    @GetMapping("/private")
    public  String privateMessage(){
        return "Accessed for valid user";
    }
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
      return   userRepo.save(user);
    }
}
