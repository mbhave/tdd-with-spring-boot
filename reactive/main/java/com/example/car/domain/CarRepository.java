package com.example.car.domain;

import reactor.core.publisher.Mono;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CarRepository extends ReactiveCrudRepository<Car, String> {

	Mono<Car> findByName(String name);

}
