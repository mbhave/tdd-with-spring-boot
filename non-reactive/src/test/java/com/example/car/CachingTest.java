package com.example.car;

import java.util.List;
import java.util.stream.Collectors;

import com.example.car.domain.Car;
import com.example.car.domain.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class CachingTest {

	@Autowired
	private CarRepository repository;

	@Before
	public void setUp() throws Exception {
		this.repository.save(new Car("prius", "hybrid"));
		this.repository.save(new Car("model-x", "electric"));
	}

	@Test
	public void findAll_ReturnsCachedValue() throws Exception {
		List<Car> cars = (List<Car>) this.repository.findAll();
		assertThat(cars.stream().map(Car::getName)
				.collect(Collectors.toList())).containsExactly("prius", "model-x");

		this.repository.save(new Car("passat", "gas"));

		List<Car> updatedCars = (List<Car>) this.repository.findAll();
		assertThat(updatedCars.stream().map(Car::getName)
				.collect(Collectors.toList())).containsExactly("prius", "model-x");
	}
}
