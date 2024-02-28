package com.app.EpicEnergyCRM.security;

import com.app.EpicEnergyCRM.enums.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityChain {

    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/auth/**").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/utente").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/utente/**").hasAuthority(Ruolo.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/cliente").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/cliente/**").hasAuthority(Ruolo.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/fattura").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/fattura/**").hasAuthority(Ruolo.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/indirizzo").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/indirizzo/**").hasAuthority(Ruolo.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").denyAll());

        return httpSecurity.build();
    }

}
