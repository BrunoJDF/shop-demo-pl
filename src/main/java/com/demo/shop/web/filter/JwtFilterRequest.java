package com.demo.shop.web.filter;

import com.demo.shop.domain.service.UserApiService;
import com.demo.shop.web.config.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {
    private final JwtUtils jwt;
    private final UserApiService userApiService;

    public JwtFilterRequest(JwtUtils jwt, UserApiService userApiService) {
        this.jwt = jwt;
        this.userApiService = userApiService;
    }

    /**
     * Primero se obtiene la cabecera de autentificacion
     * Luego se valida que exista la modalidad de autenticacion Bearer
     * Una vez realizado se recupera el token que existe despues de la posicion 7 de "Bearer "
     * Se utiliza el JwtUtils para obtener el usuario de la solicitud y almacenarlo
     * Se realiza validaciones de que usuario sea diferente de null y en la aplicacion de no exista autenticacion
     * Luego se utiliza UserApiService para obtener los que existe en la aplicacion y compararlo con lo que se envia en la solicitud
     * Se raliza la validacion del token que se envio en el request y del usuario que existe en la aplicacion si retorna true, continua con la autenticacion
     * Se invoca UsernamePasswordAuthenticationToken, para almacenar las credenciales que logro la autenticacion
     * en setDetails se almacena con el request los datos de la solicitud(sesion, navegador, ip, etc)
     * Y se almacena la autenticacion en el contexto de la aplicacion
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String jwt = authorizationHeader.substring(7);
            String username = this.jwt.extractUserFromToken(jwt);
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userApiService.loadUserByUsername(username);
                if(this.jwt.validatorJwt(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
