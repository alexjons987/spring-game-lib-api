package se.alexjons.gamelib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception { // Filter chain for security
        httpSec
                .csrf(csrf -> csrf.disable()) // Disabled CSRF to allow POST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll() // Allow everything under /public
                        .requestMatchers("/api/admin/**").authenticated()) // Require auth under /admin
                .httpBasic(Customizer.withDefaults());

        return httpSec.build();
    }

    @Bean
    public UserDetailsService userDetailsService() { // In-memory users
        UserDetails admin = User
                .withUsername("alex")
                .password(passwordEncoder().encode("admin")) // Use our encoding for passwords
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // Plain-text is not allowed, so we set a standard encoder
        return new BCryptPasswordEncoder();
    }
}
