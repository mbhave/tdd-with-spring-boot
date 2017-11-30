package com.example.car.web;

import com.example.car.domain.Car;
import com.example.car.domain.CarRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarsController {

	private final CarRepository carRepository;

	public CarsController(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@GetMapping("/cars/{name}")
	public Car getCar(@PathVariable String name) {
		Car car = this.carRepository.findByName(name);
		if (car == null) {
			throw new CarNotFoundException();
		}
		return car;
	}

}
