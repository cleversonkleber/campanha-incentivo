package com.campanha_incentivo.configs;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	 private final TokenConfig configTokenConfig;


	    
	    public SecurityFilter(TokenConfig configTokenConfig) {
	        this.configTokenConfig = configTokenConfig;
	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
	        String authorizadHeader = request.getHeader("Authorization");
	        if (Strings.isNotEmpty(authorizadHeader) && authorizadHeader.startsWith("Bearer ")) {
	            String token =  authorizadHeader.substring("Bearer ".length());

	            Optional<JWTUserData> aptUser = configTokenConfig.validateToken(token);
	            
	            if (aptUser.isPresent()) {
	                JWTUserData jwtUserData = aptUser.get();
	                List<SimpleGrantedAuthority> authorities = jwtUserData.roles().stream()
	                        .map(SimpleGrantedAuthority::new)
	                        .toList();

	                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtUserData,null,authorities);
	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            }
	            filterChain.doFilter(request, response);
	        }else{
	            filterChain.doFilter(request, response);
	        }
	    }


}
