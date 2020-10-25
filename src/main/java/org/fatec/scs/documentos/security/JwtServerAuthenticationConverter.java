package org.fatec.scs.documentos.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {

	@Override
	public Mono<Authentication> convert(ServerWebExchange exchange) {
		return Mono.justOrEmpty(exchange)
				.flatMap(auth -> Mono.justOrEmpty(auth.getRequest().getHeaders().get("Authorization")))
				.filter(it -> !it.isEmpty())
				.map(it -> it.get(0))
				.map(it -> new UsernamePasswordAuthenticationToken(it, it));
	}

}
