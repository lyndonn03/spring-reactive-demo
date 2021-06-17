package io.lpamintuan.springreactivedemo.controllers;

import java.time.Duration;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.lpamintuan.springreactivedemo.models.Song;
import io.lpamintuan.springreactivedemo.services.SongService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/songs")
public class SongController {
    
    private final SongService songService;

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<Song> getSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public Mono<Song> getSong(@PathVariable String id) {
        return songService.getSong(id);
    }

    @GetMapping(value = "/sse", produces = "audio/mpeg")
    @ResponseBody
    public Flux<byte[]> stream() {
        return Flux.just(new byte[100]).delayElements(Duration.ofMillis(500));
    }

}
