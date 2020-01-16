package com.netcracker.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger LOG = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public JwtToken createToken(String email) {
        LOG.debug("[ (email : {})", email);

        Claims claims = Jwts.claims().setSubject(email);

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + validityInMilliseconds);
        LOG.debug("now : {}, expirationDate : {}", now, expirationDate);


        String tokenAsString = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        JwtToken token = new JwtToken(tokenAsString, email);

        LOG.debug("] (token : {})", token);
        return token;
    }

    public JwtToken resolveToken(HttpServletRequest req) {
        LOG.debug("[");

        String bearerToken = req.getHeader("Authorization");
        LOG.trace("bearerToken : {}", bearerToken);

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {//в примере была так: Bearer_
            String tokenAsString = bearerToken.substring(7);

            JwtToken token = new JwtToken(tokenAsString, resolveUsernameFromToken(tokenAsString));

            LOG.debug("] (token : {})", token);
            return token;
        }

        LOG.debug("] (token : null)");
        return null;
    }

    public void addTokenToResponse(HttpServletResponse response, JwtToken token) {
        LOG.debug("[ (token : {})", token);

        response.setHeader("Authorization", "Bearer " + token.getTokenAsString());

        LOG.debug("]");
    }
    //возвращает имя пользователя по токену
    private String resolveUsernameFromToken(String token){
        LOG.trace("[ (token : {})", token);

        String email = Jwts.parser().setSigningKey(secretKey).
                parseClaimsJws(token).getBody().getSubject();

        LOG.trace("] (email : {})", email);
        return email;
    }



}
