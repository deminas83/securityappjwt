package ru.demin.spbootsecurity.spbootsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.demin.spbootsecurity.spbootsecurity.services.PersonDetailsService;

@Configuration
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz

//                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/auth/login", "/auth/register", "/css/**").permitAll()
                                .anyRequest().hasAnyRole("USER", "ADMIN")
                                        )
                                .formLogin(formLogin -> formLogin
                                        .loginPage("/auth/login")
                                        .loginProcessingUrl("/process_login")
                                        .defaultSuccessUrl("/hello", true)
                                        .failureUrl("/auth/login?error")
                                        .permitAll()
                                )
                                .logout(logout -> logout
                                        .logoutSuccessUrl("/auth/login")
                                        .permitAll()
                                );
                        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
