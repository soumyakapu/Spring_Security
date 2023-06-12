package spring_security_basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring_security_basic.filter.UserFilter;

@Configuration
public class WebSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(http->http
//                                .requestMatchers("/","/create","/h2-console").permitAll()
//                .requestMatchers("/h2-console").permitAll()
//                .requestMatchers("/h2-console/**").permitAll()
//                .requestMatchers("/private").authenticated()
                                .anyRequest().permitAll()
        );
//                .formLogin(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.addFilterBefore(new UserFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers(header->header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
       return httpSecurity.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new InMemoryUserDetailsManager(User.withUsername("Soumya")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build()
//        );
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
