package com.petrov.webfluxsecurity.security;

import com.petrov.webfluxsecurity.exception.UnauthorizedException;
import com.petrov.webfluxsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.petrov.webfluxsecurity.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final UserRepository userRepository;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        CustomPrincipal principal = (CustomPrincipal)authentication.getPrincipal();

        return userRepository.findById(principal.getId())
                .filter(UserEntity::isEnabled)
                .switchIfEmpty(Mono.error(new UnauthorizedException("User Disabled")))
                .map(user -> authentication);
    }
}
