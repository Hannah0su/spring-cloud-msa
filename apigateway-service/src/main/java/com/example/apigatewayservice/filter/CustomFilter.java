package com.example.apigatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

	public CustomFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		//PreFilter
		return (exchange, chain) -> {
			//Reactive
			ServerHttpRequest serverHttpRequest = exchange.getRequest();
			ServerHttpResponse serverHttpResponse = exchange.getResponse();

			log.info("Custom PRE filter : request id -> {}", serverHttpRequest.getId());

			//PostFilter
			return chain.filter(exchange).then(Mono.fromRunnable(() -> log.info("Custom POST filter : response code -> {}", serverHttpResponse.getStatusCode())));
		};
	}

	public static class Config {
		//Configuration 삽입
	}

}
