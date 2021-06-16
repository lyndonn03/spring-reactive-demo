package io.lpamintuan.springreactivedemo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import io.lpamintuan.springreactivedemo.models.TestModel;
import reactor.core.publisher.Mono;

public class TestControllerTest {

    private WebTestClient testClient;

    @BeforeEach
    public void setUp() {
        testClient = WebTestClient.bindToController(new TestController()).build();
    }

    @Test
    public void test_shouldReturnListOfNamesAndAges() {
        
        testClient.get().uri("/test")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
                .jsonPath("$[0].name").isEqualTo("Lyndonn")
                .jsonPath("$[1].name").isEqualTo("Allen");

    }

    @Test
    public void test_shouldReturnThePersonObject_withTheIdAsVariable() {
        
        testClient.get().uri("/test/1")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
                .jsonPath("$.name").isEqualTo("Lyndonn")
                .jsonPath("$.age").isEqualTo(23);   
    }

    @Test
    public void test_shouldReturnThePersonObject_ifITAddsSuccessfullyInTheList() {
        Mono<TestModel> data = Mono.just(new TestModel(3, "John Doe", 40));

        testClient.post().uri("/test")
            .contentType(MediaType.APPLICATION_JSON)
            .body(data, TestModel.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
                .jsonPath("$.name").isEqualTo("John Doe");

    }
    
}
