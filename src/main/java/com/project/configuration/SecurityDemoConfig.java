package com.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de sécurité réservée au profil de démonstration "demo".
 * Elle assouplit volontairement l'accès (toutes les routes publiques) afin de
 * permettre la démonstration et la génération de captures d'écran sans écran de
 * connexion. Ce bean est prioritaire (@Order(1)) vis-à-vis de {@link WebSecurity}.
 *
 * En dehors du profil "demo", la sécurité de production (WebSecurity) s'applique.
 */
@Configuration
@EnableWebSecurity
@Order(1)
@Profile("demo")
public class SecurityDemoConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain demoFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());
        return http.build();
    }
}
