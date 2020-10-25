package org.fatec.scs.documentos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtSigner {

	private final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.ES256);
	
	public String createJwt(String userId) {
		return Jwts.builder()
				.signWith(keyPair.getPrivate(), SignatureAlgorithm.ES256)
				.setSubject(userId)
				.setIssuer("identity")
				.setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(60))))
				.setIssuedAt(Date.from(Instant.now()))
				.compact();
	}
	
	public Jws<Claims> validateJws(String jwt){
		return Jwts.parserBuilder()
				.setSigningKey(keyPair.getPublic())
				.build()
				.parseClaimsJws(jwt);
	}
}
