package com.rga.auth.security;

import com.rga.auth.model.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  @Value("${jwt.secretKey}")
  private String secret;

  @Value("${jwt.tokenExpirationAfterDays}")
  private Long tokenExpirationAfterDays;


  @PostConstruct
  protected void init() {
    secret = Base64.getEncoder().encodeToString(secret.getBytes());
  }

  public String createToken(AuthUser authUser) {

    Map<String, Object> claims = new HashMap<>();

    claims.put("id", authUser.getId());

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(authUser.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(tokenExpirationAfterDays)))
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public boolean validate(String token) {
    try {
      Jwts
          .parser()
          .setSigningKey(secret)
          .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String getUserNameFromToken(String token) {
    try {
      return Jwts
          .parser()
          .setSigningKey(secret)
          .parseClaimsJws(token)
          .getBody()
          .getSubject();
    } catch (Exception e) {
      return "bad token";
    }
  }


}
