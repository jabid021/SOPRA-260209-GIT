// MARTIN

package biblio.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig 
{
    @Autowired
    private JwtHeaderFilter jwtHeaderFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
    {
        http.authorizeHttpRequests(authorization -> 
        {
            authorization.requestMatchers("/api/auth").permitAll();
            authorization.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll();
            authorization.requestMatchers("/**").authenticated();
        });
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
        http.cors(Customizer.withDefaults());
        http.addFilterBefore(this.jwtHeaderFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() 
    {
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));

        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception 
    {
        return config.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
}
