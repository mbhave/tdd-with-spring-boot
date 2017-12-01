package com.example.car;

import com.example.car.domain.Car;
import com.example.car.domain.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ReactiveMongoOperations operations;

	@Before
	public void setUp() throws Exception {
		this.operations
				.createCollection(Car.class, CollectionOptions.empty().size(1024 * 1024).maxDocuments( 100).capped())
				.then()
				.block();

		this.carRepository
				.save(new Car("prius", "hybrid"))
				.then()
				.block();
	}

	@Test
	public void getCar_WithName_ReturnsCar() {
		Car car = this.webTestClient.get().uri("/cars/{name}", "prius")
				.exchange().expectStatus().isOk()
				.expectBody(Car.class).returnResult().getResponseBody();
		assertThat(car.getName()).isEqualTo("prius");
		assertThat(car.getType()).isEqualTo("hybrid");
	}

}
