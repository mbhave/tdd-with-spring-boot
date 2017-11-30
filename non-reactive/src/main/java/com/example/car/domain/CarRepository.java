package com.example.car.domain;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car,String> {

	Car findByName(String name);

}
