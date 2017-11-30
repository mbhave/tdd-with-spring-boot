package com.example.car.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTests {

	@Autowired
	private CarRepository repository;

	@Before
	public void setUp() throws Exception {
		this.repository.save(new Car("prius", "hybrid"));
	}

	@Test
	public void findByName_ReturnsCar() throws Exception {
		Car car = this.repository.findByName("prius");
		assertThat(car.getName()).isEqualTo("prius");
		assertThat(car.getType()).isEqualTo("hybrid");
	}
}