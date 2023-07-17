package com.neo.security.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.neo.security.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
	
	/* Secret is stored in application.properties */
	@Value("${app.secret}")
	private String secret;

	public String generateToken(User user) {

		JwtBuilder builder = Jwts.builder();

		builder.setSubject(user.getId() + "," + user.getEmail())
		.setIssuer("ROBIN GEORGE")
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
		.signWith(SignatureAlgorithm.HS512, secret.getBytes());

		return builder.compact();
	}
	

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
			
			return true;
			
		} catch (ExpiredJwtException e) {
			LOGGER.error("JWT Expired" , e);
		}catch (IllegalArgumentException e) {
			LOGGER.error("Token is empty",e);
		}catch (MalformedJwtException e) {
			LOGGER.error("JWT is invalid",e);
		}catch (UnsupportedJwtException e) {
			LOGGER.error("JWT is not supported",e);
		}catch (SignatureException e) {
			LOGGER.error("Signature validation failed",e);
		}
		return false;
	}
	
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	
	private Claims parseClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
}
