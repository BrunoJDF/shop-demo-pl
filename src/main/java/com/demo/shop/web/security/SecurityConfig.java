package com.demo.shop.web.security;

import com.demo.shop.domain.service.UserApiService;
import com.demo.shop.web.filter.JwtFilterRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserApiService user;
    private final JwtFilterRequest filterRequest;

    public SecurityConfig(UserApiService user, JwtFilterRequest filterRequest) {
        this.user = user;
        this.filterRequest = filterRequest;
    }

    /**
     * Se envia el usuario custom almacenado en UserApiservice para no utilizar la configuracion standard de SpringSecurity
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(user);
    }

    /**
     * Lo que se realiza aqui es permitir cualquier request realizada a cualquier endpoint que termine en "/authenticate"
     * Cualquier otra esta bloqueda por autenticacion
     * En la linea 47 se crea un filtro para que las solicitudes esten desbloqueadas cuando se realice la autenticacion
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Se utiliza para elegir explicitamente a spring como gestor de la aunteticacion
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
