package com.internshipfinder.demo.business.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internshipfinder.demo.business.dto.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    @Value("{security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private final long validityInMilliseconds = 3600000; // 1h

    private final MyUserDetails myUserDetails;

    //private ObjectMapper oMapper;

    public JwtTokenProvider(MyUserDetails myUserDetails) {
        this.myUserDetails = myUserDetails;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, UserType userType) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", new SimpleGrantedAuthority(userType.getAuthority()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    public Authentication getAuthentication(String token) throws Exception {
        UserDetails userDetails = myUserDetails.loadUserByUsername(this.getUsername(token), UserType.valueOf(this.getUserType(token)));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getUserType(String token) {
        LinkedHashMap<String, String> authorities = (LinkedHashMap<String, String>) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("auth");
        return authorities.get("authority");
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new Exception(); //TODO Custom exception
        }
    }
}
