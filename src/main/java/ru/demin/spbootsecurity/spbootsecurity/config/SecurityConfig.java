package ru.demin.spbootsecurity.spbootsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import ru.demin.spbootsecurity.spbootsecurity.security.AuthProviderImpl;


@EnableWebSecurity
public class SecurityConfig {

    private final AuthProviderImpl authProvider;

    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    protected void configure (AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider);
    }
}
