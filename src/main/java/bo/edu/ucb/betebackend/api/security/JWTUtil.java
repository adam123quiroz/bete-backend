package bo.edu.ucb.betebackend.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    private static final String KEY = "b3t3";
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject()
                    .equals(userDetails.getUsername());
            return true;
/*            return userDetails
                    .getUsername()
                    .equals(extractUsername(token)) && isTokenExpired(token);*/
        }
        // we can safely trust the JWT

        catch (Exception ex) {
            // the parsed JWT did not have the sub field
//            System.out.println(mce.fillInStackTrace().getMessage());
            System.out.println("mrd");
            return false;

        } // the parsed JWT had a sub field, but its value was not equal to 'jsmith'

    }

    public String extractUsername(String token) {
        try {

            return getClaims(token)
                    .getSubject();
        } catch (Exception exception) {
            System.out.println("gggggg  " + exception);
            return "ddd";
        }
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
