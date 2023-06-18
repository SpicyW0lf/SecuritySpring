package com.petrov.webfluxsecurity.repository;

import com.petrov.webfluxsecurity.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<UserRepository, Long> {

    Mono<UserEntity> findByUsername(String username);
}
