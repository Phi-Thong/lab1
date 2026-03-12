package com.poly.config;

import com.poly.service.DatabaseUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    private final DatabaseUserService databaseUserService;

    public SecurityConfig(DatabaseUserService databaseUserService) {
        this.databaseUserService = databaseUserService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/poly/**").authenticated();
            auth.anyRequest().permitAll();
        });

        http.userDetailsService(databaseUserService);

        http.formLogin(form -> {
            form.loginPage("/login/form");
            form.loginProcessingUrl("/login/check");
            form.defaultSuccessUrl("/", true);
            form.failureUrl("/login/failure");
            form.usernameParameter("username");
            form.passwordParameter("password");
            form.permitAll();
        });

        http.rememberMe(config -> {
            config.tokenValiditySeconds(3 * 24 * 60 * 60);
            config.rememberMeCookieName("remember-me");
            config.rememberMeParameter("remember-me");
        });

        http.logout(logout -> {
            logout.logoutUrl("/logout");
            logout.logoutSuccessUrl("/login/exit");
            logout.clearAuthentication(true);
            logout.invalidateHttpSession(true);
            logout.deleteCookies("remember-me");
        });

        return http.build();
    }
}