package se.alexjons.gamelib.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import se.alexjons.gamelib.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception { // Filter chain for security
        httpSec
                .csrf(csrf -> csrf.disable()) // Disabled CSRF to allow POST
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .anyRequest().authenticated()
                        /*
                        .requestMatchers(HttpMethod.GET, "/api/public/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("ADMIN")
                         */
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                // .userDetailsService(customUserDetailService)
                // .userDetailsService(userDetailsService())
                // .httpBasic(Customizer.withDefaults())

        return httpSec.build();
    }

    /*

    Comment out this bean to prevent stackoverflow exception

    @Bean
    public UserDetailsService userDetailsService() { // In-memory users
        UserDetails admin = User
                .withUsername("superadmin")
                .password(passwordEncoder().encode("superadmin123")) // Use our encoding for passwords
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
     */

    @Bean
    public PasswordEncoder passwordEncoder() { // Plain-text is not allowed, so we set a standard encoder
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
