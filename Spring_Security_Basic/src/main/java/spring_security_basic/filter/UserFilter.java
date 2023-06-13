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
import java.nio.file.AccessDeniedException;
import java.util.List;

@Component
public class UserFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     String accessToken = request.getHeader("accessToken");
     String uri = request.getRequestURI();
     if("/".equals(uri) || "/create".equals(uri)){
         filterChain.doFilter(request, response);
         return;
     } else if ("/private".equals(uri)) {
         if ("Sonu".equals(accessToken)){
             UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("Sonu","", List.of(new SimpleGrantedAuthority("ADMIN")));
             SecurityContextHolder.getContext().setAuthentication(token);
             filterChain.doFilter(request, response);
             return;
         }
            throw new AccessDeniedException("Please give the valid token");

     }

     else {
         response.getWriter().println("user not allowed");

     }
    }
}
