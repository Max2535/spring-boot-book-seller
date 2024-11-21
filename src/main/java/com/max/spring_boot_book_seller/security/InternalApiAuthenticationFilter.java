package com.max.spring_boot_book_seller.security;

import com.max.spring_boot_book_seller.util.SecurityUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class InternalApiAuthenticationFilter extends OncePerRequestFilter {

    private final String accessKey;

    public InternalApiAuthenticationFilter(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)throws ServletException {
        return !request.getRequestURI().startsWith("/api/internal");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, java.io.IOException {

       try{
           String requestKey = SecurityUtils.extractAuthTokenFromRequest(request);

           if(requestKey == null || !requestKey.equals(accessKey)){
               log.warn("Internal key endpoint requested without/with invalid key uri: {}",request.getRequestURI());
               throw new RuntimeException("UNAUTHORIZED");
           }

           UserPrincipal user = UserPrincipal.createSuperUser();
           UsernamePasswordAuthenticationToken authentication =
                   new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

           SecurityContextHolder.getContext().setAuthentication(authentication);
       } catch (Exception ex) {
           log.error("Could not set user authentication in security context",ex);
       }finally {

       }

       filterChain.doFilter(request,response);

    }
}
