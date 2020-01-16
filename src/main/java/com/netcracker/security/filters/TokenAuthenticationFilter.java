package com.netcracker.security.filters;

import com.netcracker.security.jwt.JwtToken;
import com.netcracker.security.jwt.JwtTokenProvider;
import com.netcracker.security.services.UserSpringDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//каждый запрос который приходит на сервер нужно проверять на
//наличие токена что здесь и происходит
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private final JwtTokenProvider jwtTokenProvider;
    private final UserSpringDetailsService springDetailsService;

    public TokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserSpringDetailsService springDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.springDetailsService = springDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        LOG.debug("[");
        //получаю токен из запроса
        JwtToken accessToken = jwtTokenProvider.resolveToken(httpServletRequest);
        LOG.trace("accessToken : {}", accessToken);

        //проверяю есть токен или нет
        if (accessToken != null) {
            // TODO: 21.11.2019 add check by username + token
            //получаю jwt юсера  ро имени из токена
            UserDetails userDetails = springDetailsService.loadUserByUsername(accessToken.getUserName());

            LOG.trace("userDetails : {}", userDetails);

            final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            //может быть нужна проверка
//            if (authentication != null) {
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
            //происходит аунтетинтификация запроса(запрос проходит проверку)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        LOG.debug("]");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
