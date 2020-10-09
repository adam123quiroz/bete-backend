package bo.edu.ucb.betebackend.api.security.filter;

import bo.edu.ucb.betebackend.api.security.JwtSecondUtil;
import bo.edu.ucb.betebackend.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException {

        // obtenemos el body de la peticion que asumimos viene en formato JSON
        InputStream body = req.getInputStream();

        // Asumimos que el body tendra el siguiente JSON  {"username":"xxxx", "password":"xxxx"}
        // Realizamos un mapeo a nuestra clase User para tener ahi los datos
        User user = new ObjectMapper().readValue(body, User.class);

        // Finalmente autenticamos
        // Spring comparar el user/password recibidos
        // contra el que definimos en la clase SecurityConfig
        // el Collections.emptyList() es el lugar para los roles o authorities, pero no es necesario en este primer filtro
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.emptyList()
                )
        );

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) {

        HttpServletResponse response;
        response = res;
        String clientOrigin = req.getHeader("origin");

         /*
         String audience = request.getHeader("User-Agent");

         UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
         OperatingSystem agent = userAgent.getOperatingSystem();

         System.out.print("You're a ");
         System.out.print(agent.getName() + " about a: ");
         System.out.println(agent.getDeviceType().getName());

         System.out.println(audience);
         */

        response.addHeader("Access-Control-Allow-Origin", clientOrigin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET,  DELETE, PUT");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type, Origin, Authorization, X-Auth-Token");

        // Si la autenticacion fue exitosa, agregamos el token a la respuesta
        JwtSecondUtil.addAuthentication(response, auth.getName());

        logger.info("MRASDJSAJKDNSBKDJUSHN");
    }
}