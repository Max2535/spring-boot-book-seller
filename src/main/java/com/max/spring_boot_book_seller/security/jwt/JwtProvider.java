package com.max.spring_boot_book_seller.security.jwt;

import com.max.spring_boot_book_seller.security.UserPrincipal;
import com.max.spring_boot_book_seller.util.SecurityUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;

@Component
public class JwtProvider implements IJwtProvider {

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private String JWT_EXPIRATION_IN_MS;

    @Override
    public String generateToken(UserPrincipal auth){
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        return Jwts.builder()
                .setSubject(auth.getUsername())
                .claim("roles", authorities)
                .claim("userId", auth.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(JWT_EXPIRATION_IN_MS)))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request){
        Claims claims = extractClaims(request);

        if(claims == null){
            return null;
        }

        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());

        UserDetails userDetails = UserPrincipal.builder()
                .id(userId)
                .username(username)
                .authorities(authorities)
                .build();

        if (username == null){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null, authorities);
    }

    @Override
    public boolean validateToken(HttpServletRequest request){
        Claims claims = extractClaims(request);

        if(claims == null){
            return false;
        }

        return !claims.getExpiration().before(new Date());
    }

    public Claims extractClaims (HttpServletRequest request){
        String token = SecurityUtils.extractAuthTokenFromRequest(request);

        if(token == null){
            return null;
        }

        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
