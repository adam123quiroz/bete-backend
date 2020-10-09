package bo.edu.ucb.betebackend.api.security;

import bo.edu.ucb.betebackend.api.security.filter.JwtFilter;
import bo.edu.ucb.betebackend.api.security.filter.LoginFilter;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    final private BeteUserDetailsService userDetailsService;
    final private JwtFilter jwt;

    public SecurityConfig(BeteUserDetailsService userDetailsService, JwtFilter jwt) {
        this.userDetailsService = userDetailsService;
        this.jwt = jwt;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/authenticate", "/user/registration", "/payment/get-paypal-transaction").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

//                .addFilterBefore(new LoginFilter("/auth/authenticate", authenticationManager()),
//                        UsernamePasswordAuthenticationFilter.class)
                // Las demás peticiones pasarán por este filtro para validar el token
                .addFilterBefore(jwt,
                        UsernamePasswordAuthenticationFilter.class);



    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security",
                "/swagger-ui.html", "/webjars/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
