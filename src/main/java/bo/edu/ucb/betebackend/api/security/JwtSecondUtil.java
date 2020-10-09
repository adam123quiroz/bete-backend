package bo.edu.ucb.betebackend.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class JwtSecondUtil {

    final static Logger logger = LoggerFactory.getLogger(JwtSecondUtil.class);
    public static String KEYSECRET = "laclavesecretsdfdsfdsfdsfdsfdsfsdfdsfdsfdsfdsfdsfsdfsdfdsfsdfdsfdsfsdfdsfdsfdsfdsfdsfdsfdsfa";

    // Metodo para crear el JWT y enviarlo al cliente en el header de la respuesta
    public static void addAuthentication(HttpServletResponse res, String username) {

        String token = Jwts.builder()
                .setSubject(username)
                // Vamos a asignar un tiempo de expiracion de 12 horas
                .setExpiration(new Date(System.currentTimeMillis() + 43200000))
                // Hash con el que firmaremos la clave
                .signWith(SignatureAlgorithm.HS512, KEYSECRET)
                .compact();
        //agregamos al encabezado el token
        res.addHeader("Authorization", token);
    }

    // Metodo para validar el token enviado por el cliente
    public static Authentication getAuthentication(HttpServletRequest request, UserDetails userDetails) {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");
        // si hay un token presente, entonces lo validamos
        if (token != null && token.startsWith("Bearer")) {
            String jwt = token.substring(7);

            String user = Jwts.parser()
                    .setSigningKey(KEYSECRET)
                    .parseClaimsJws(jwt) //este metodo es el que valida
                    .getBody()
                    .getSubject();

            // Recordamos que para las demas peticiones que no sean /login
            // no requerimos una autenticacion por username/password
            // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password, pero debemos consultar nuevamente los roles
            // seguramente hay una mejor forma de implementar roles, pero no esta del todo mal volver a consultarlos, puesto que si hubo un cambio
            // en la asignación de roles a un usuario, se le otorgara o se le denegara el acceso de forma automatica, y no hay que esperar al vencimiento del token

            return user != null ?
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) :
                    null;

        }

        return null;
    }

}