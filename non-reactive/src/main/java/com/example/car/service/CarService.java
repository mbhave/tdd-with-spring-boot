package com.example.car.service;

import com.example.car.domain.Car;
import com.example.car.domain.CarRepository;
import com.example.car.web.CarNotFoundException;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CarService {

	private CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Cacheable("cars")
	public Car getCarDetails(String name) {
		Car car = carRepository.findByName(name);
		if(car == null) {
			throw new CarNotFoundException();
		}
		return car;
	}
}
