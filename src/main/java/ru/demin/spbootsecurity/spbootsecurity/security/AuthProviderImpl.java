package ru.demin.spbootsecurity.spbootsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.demin.spbootsecurity.spbootsecurity.services.PersonDetailsService;

import java.util.List;



@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= authentication.getName();

        UserDetails userDetails = personDetailsService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();

        if (!password.equals(userDetails.getPassword()))
            throw new BadCredentialsException("incorrect password");

        return new UsernamePasswordAuthenticationToken(userDetails, password, List.of());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
