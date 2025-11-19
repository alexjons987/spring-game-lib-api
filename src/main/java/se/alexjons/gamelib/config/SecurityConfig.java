package se.alexjons.gamelib.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import se.alexjons.gamelib.repository.UserRepository;
import se.alexjons.gamelib.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception { // Filter chain for security
        httpSec
                .csrf(csrf -> csrf.disable()) // Disabled CSRF to allow POST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/public/**").permitAll() // Allow GET under /public
                        .requestMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("ADMIN"))
                .userDetailsService(customUserDetailService)
                .userDetailsService(userDetailsService())
                .httpBasic(Customizer.withDefaults());

        return httpSec.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // In-memory users

        UserDetails admin = User
                .withUsername("superadmin")
                .password(passwordEncoder().encode("superadmin123")) // Use our encoding for passwords
                .roles("ADMIN")
                .build();

        UserDetails user = User
                .withUsername("userIN")
                .password(passwordEncoder().encode("user123")) // Use our encoding for passwords
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // Plain-text is not allowed, so we set a standard encoder
        return new BCryptPasswordEncoder();
    }
}
