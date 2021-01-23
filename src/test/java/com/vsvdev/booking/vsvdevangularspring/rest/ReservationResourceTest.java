package com.vsvdev.booking.vsvdevangularspring.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vsvdev.booking.vsvdevangularspring.VsvdevAngularSpringApplication;
import static io.restassured.RestAssured.get;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VsvdevAngularSpringApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ReservationResourceTest {
	
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void test() {
		get("http://localhost:8080/room/reservation/v1/1").then().assertThat().statusCode(200);
	}

}
