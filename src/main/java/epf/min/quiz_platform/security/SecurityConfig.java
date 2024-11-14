package epf.min.quiz_platform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity  // Active la configuration Spring Security
public class SecurityConfig {

    private final JWTFilter jwtFilter;

    // Injecte ton JWTFilter via le constructeur
    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // Configure la chaîne de filtres de sécurité
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactiver CSRF pour les API REST (toujours utilisé avec JWT)
                .csrf(csrf -> csrf.disable())

                // Configuration de la gestion des sessions en mode stateless
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Ajoute ton JWTFilter avant UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                // Configuration des règles d'autorisation
                .authorizeHttpRequests(authz ->
                        authz
                                .requestMatchers("/public/**").permitAll() // Permet l'accès aux routes publiques
                                .anyRequest().authenticated()  // Toutes les autres routes nécessitent une authentification
                );

        return http.build();
    }
}
