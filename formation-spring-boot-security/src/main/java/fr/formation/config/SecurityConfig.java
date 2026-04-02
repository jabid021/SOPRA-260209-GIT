package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // Activer les annotation @PreAuthorize / @PostAuthorize
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, DemoHeaderFilter demoHeaderFilter) throws Exception {
        // Configuration des accès : qui a droit de voir quoi
        http.authorizeHttpRequests(auth -> {
            // On commence toujours par le plus spécifique, pour terminer par le plus général

            // auth.requestMatchers("/matiere").permitAll();

            // auth.requestMatchers("/matiere").permitAll();
            // auth.requestMatchers("/matiere").hasRole("ADMIN");

            // auth.requestMatchers("/matiere", "/utilisateur").hasRole("ADMIN");
            // auth.requestMatchers("/matiere", "/utilisateur").hasAnyRole("ADMIN", "USER");
            // auth.requestMatchers("/matiere", "/utilisateur").hasAuthority("ROLE_ADMIN");

            // auth.requestMatchers(HttpMethod.POST, "/matiere", "/utilisateur").hasRole("ADMIN");

            // auth.requestMatchers("/matiere").hasAnyRole("ADMIN", "USER");

            auth.requestMatchers("/api/auth").permitAll();

            // Uniquement les utilisateurs authentifiés partout sur l'application
            auth.requestMatchers("/**").authenticated();
        });

        // Configuration de la form de connexion
        http.formLogin(Customizer.withDefaults());

        http.httpBasic(Customizer.withDefaults());

        // On intègre le filtre Demo Header Filter AVANT le filtre UsernamePasswordAuthenticationFilter
        // http.addFilterBefore(demoHeaderFilter, UsernamePasswordAuthenticationFilter.class);

        // On désactive la protection CSRF (Cross-Site Request Forgery)
        // http.csrf(csrf -> csrf.disable());
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

        return http.build();
    }

    // @Bean
    UserDetailsService inMemory() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails ud = User.builder()
            .username("jeremy")
            // .password("{noop}123456")
            // .password("123456")
            .password("$2a$10$wZppzN.nawtOTtOFaunubeAYHNCWvOrwsNfKStb8n/5L3bTwpxWUW")
            .roles("ADMIN", "USER")
            .build()
        ;

        manager.createUser(ud);

        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance(); // PAS OUF

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("123456"));

        return passwordEncoder;
    }

    // Permet d'ajouter l'AuthenticationManager de Spring Security dans le contexte de Spring, pour pouvoir le récupérer
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
