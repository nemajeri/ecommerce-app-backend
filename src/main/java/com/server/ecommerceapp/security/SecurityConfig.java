package com.server.ecommerceapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.oauth2Login().and()
                .csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login").permitAll();
                    auth.anyRequest().authenticated();
                }).build();
    }

    //Primjer user client registration repozitorijuma
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration client1 = ClientRegistration.withRegistrationId("client1")
                .clientId("client1")
                .clientSecret("secret")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("read", "write")
                .authorizationUri("https://dev-yt3n6ujq7p3f0n7b.us.auth0.com")
                .tokenUri("https://example.com/oauth2/token")
                .userInfoUri("https://example.com/oauth2/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .clientName("Client 1")
                .build();

        return new InMemoryClientRegistrationRepository(client1);
    }
}
