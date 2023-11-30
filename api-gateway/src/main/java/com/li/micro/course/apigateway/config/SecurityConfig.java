package com.li.micro.course.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 28.11.2023
 * Time: 12:17
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http = http.csrf((csrf) -> csrf.disable())
                .authorizeExchange(exchange -> exchange.pathMatchers("/eureka/**").permitAll().anyExchange().authenticated())
                .oauth2ResourceServer((oauth2ResourceServer) -> oauth2ResourceServer.jwt(Customizer.withDefaults()));
//                .oauth2ResourceServer((oauth2ResourceServer) -> oauth2ResourceServer.jwt((jwt) -> jwt.publicKey(publicKey())));
        return http.build();
    }
}
