package com.CathyB.stockMarketTrend;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class JWTUtil {

  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

  public String generateToken(UserDetails  userDetails){




    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
        .signWith(SignatureAlgorithm.HS512,key)
        .compact();
  }
  public boolean validateToken(String token){



    try{ Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token);

    return true;
    } catch (SignatureException e) {
      System.out.println("Invalid token signature or expired token");
      return  false;
    } catch (Exception e) {
      System.out.println("Invalid token");
      return false;
    }
  }

  public String extractUsername(String token) {



    return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
  }

}
