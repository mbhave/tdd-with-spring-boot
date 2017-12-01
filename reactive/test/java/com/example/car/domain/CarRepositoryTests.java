package com.example.car.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import reactor.test.StepVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CarRepositoryTests {

	@Autowired
    private CarRepository carRepository;

    @Before
    public void setUp() throws Exception {
        this.carRepository.save(new Car("prius", "hybrid"))
			.then()
			.block();
    }

    @After
    public void tearDown() throws Exception {
        carRepository.deleteAll()
			.then()
			.block();
    }

    @Test
    public void findByName_returnsCar() {
		StepVerifier.create(carRepository.findByName("prius"))
				.consumeNextWith(car -> {
					assertThat(car.getName()).isEqualTo("prius");
					assertThat(car.getType()).isEqualTo("hybrid");
				})
				.verifyComplete();
    }

}