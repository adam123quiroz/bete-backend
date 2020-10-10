package bo.edu.ucb.betebackend.api.security.filter;

import bo.edu.ucb.betebackend.api.security.JwtSecondUtil;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class JwtFilter extends GenericFilterBean {
    final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    final private BeteUserDetailsService userDetailsService;

    public JwtFilter(BeteUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        // Obtenemos el token que viene en el encabezado de la peticion
        String authorizationHeader = ((HttpServletRequest) request).getHeader("Authorization");
        UserDetails userDetails = null;
        logger.info(authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String jwt = authorizationHeader.substring(7);
            String username = Jwts.parser()
                    .setSigningKey(JwtSecondUtil.KEYSECRET) //la clave secreta esta declara en JwtUtil
                    .parseClaimsJws(jwt) //este metodo es el que valida
                    .getBody()
                    .getSubject();        //extraigo el nombre de usuario del token

            if (username != null && authorizationHeader.startsWith("Bearer")) {
                userDetails = userDetailsService.loadUserByUsername(username);
            }
        }
        Authentication authentication = JwtSecondUtil.getAuthentication((HttpServletRequest) request, userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpServletResponse res;
        res = (HttpServletResponse) response;
        String clientOrigin = ((HttpServletRequest) request).getHeader("origin");
//        res.addHeader("Access-Control-Allow-Origin", clientOrigin);
        res.setHeader("Access-Control-Allow-Methods", "POST, PATCH, GET,  DELETE, PUT");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        filterChain.doFilter(request, res);
    }


}