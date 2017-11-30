package com.example.car.domain;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car,String> {

	@Override
	@Cacheable("cars")
	Iterable<Car> findAll();

	Car findByName(String name);

}
