package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

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

}
