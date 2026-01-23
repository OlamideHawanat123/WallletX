package project.hawanah.walletx.configuration.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtils {

    private Key getSigningKey(){
        String SECRET = "akbKDYAGIUWNGEYGWBGTFBWTFTEBFFGEYWGYGDYGENYWYDUEWJDEWDUWURWHNYRGFYWBFTWADUYWFEWFB5";
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String email){
         long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(),  SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
        Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
        return true;
        }catch (Exception e){
            return false;
        }
    }
}
