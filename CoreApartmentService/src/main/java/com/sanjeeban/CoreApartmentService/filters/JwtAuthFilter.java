package com.sanjeeban.CoreApartmentService.filters;

import com.sanjeeban.CoreApartmentService.dataAccessLayer.UserResidentDatabaseService;
import com.sanjeeban.CoreApartmentService.entity.UserAccount;
import com.sanjeeban.CoreApartmentService.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private UserResidentDatabaseService userResidentDatabaseService;

    @Autowired
    public JwtAuthFilter(JwtService jwtService,UserResidentDatabaseService userResidentDatabaseService) {
        this.jwtService = jwtService;
        this.userResidentDatabaseService = userResidentDatabaseService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        if(requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = requestTokenHeader.substring(7);

        Long userId = jwtService.getUserIdFromToken(token);

        if(userId!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserAccount user = userResidentDatabaseService.getUser(userId);

            String role = userResidentDatabaseService.getRoleFromUserId(userId);

            List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority("ROLE_"+role));

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null,authorityList);
            usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request,response);

    }
}
