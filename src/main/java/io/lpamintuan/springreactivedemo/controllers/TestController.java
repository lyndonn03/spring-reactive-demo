package io.lpamintuan.springreactivedemo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.lpamintuan.springreactivedemo.models.TestModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Not deleting this class
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private List<TestModel> data = new ArrayList<>(Arrays.asList(
        new TestModel(1, "Lyndonn", 23),
        new TestModel(2, "Allen", 20)
    ));

    @GetMapping
    public Flux<TestModel> test() {
        return Flux.fromIterable(data);
    }
    
    @GetMapping("/{id}")
    public Mono<TestModel> testMono(@PathVariable int id) {
        return Flux.fromIterable(data)
            .filter(p -> p.getId() == id)
            .next();
    }

    @PostMapping
    public Mono<TestModel> testPostMono(@RequestBody Mono<TestModel> body) {
        return body.doOnNext(d -> {
            data.add(d);
        });
    }
}
