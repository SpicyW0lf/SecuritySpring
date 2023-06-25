package com.petrov.webfluxsecurity.security;

import com.petrov.webfluxsecurity.exception.UnauthorizedException;
import com.petrov.webfluxsecurity.repository.UserRepository;
import com.petrov.webfluxsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.petrov.webfluxsecurity.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final UserService userService;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        CustomPrincipal principal = (CustomPrincipal)authentication.getPrincipal();

        return userService.getUserById(principal.getId())
                .filter(UserEntity::isEnabled)
                .switchIfEmpty(Mono.error(new UnauthorizedException("User Disabled")))
                .map(user -> authentication);
    }
}
