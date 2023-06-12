package spring_security_basic.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class UserFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     String accessToken = response.getHeader("x-access-token");
     String uri = request.getRequestURI();
     if("/".equals(uri) || "/create".equals(uri)){
         filterChain.doFilter(request, response);
     }
     if ("Sonu".equals(accessToken)){
         UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("Sonu","", List.of(new SimpleGrantedAuthority("ADMIN")));
         SecurityContextHolder.getContext().setAuthentication(token);
         filterChain.doFilter(request, response);
         return;
     }
     else {
         response.getWriter().println("user not allowed");
     }
    }
}
