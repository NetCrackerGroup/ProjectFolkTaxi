package com.netcracker.security.filters;

import com.netcracker.security.jwt.JwtToken;
import com.netcracker.security.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtTokenBasicAuthenticationFilter extends BasicAuthenticationFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JwtTokenBasicAuthenticationFilter.class);

    private final JwtTokenProvider tokenProvider;

    public JwtTokenBasicAuthenticationFilter(JwtTokenProvider tokenProvider, final AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.tokenProvider = tokenProvider;
    }

    @Override
    //ghjbc[klbn
    protected void onSuccessfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final Authentication authResult) {
        LOG.debug("[ (email : {}, isAuthenticated() : {}", authResult.getName(), authResult.isAuthenticated());

        if (!authResult.isAuthenticated()) {
            LOG.debug("] (is not authenticated!)");
            return;
        }

        String email = authResult.getName();
        JwtToken token = tokenProvider.createToken(email);
        LOG.trace("token : {}", token);

        // TODO: 21.11.2019 save token for user

        tokenProvider.addTokenToResponse(response, token);
    }


}
