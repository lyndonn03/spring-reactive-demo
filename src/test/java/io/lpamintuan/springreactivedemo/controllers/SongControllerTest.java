package io.lpamintuan.springreactivedemo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.*;

import java.util.UUID;

import io.lpamintuan.springreactivedemo.globals.ApiControllerAdvice;
import io.lpamintuan.springreactivedemo.globals.EntityNotFoundException;
import io.lpamintuan.springreactivedemo.models.Song;
import io.lpamintuan.springreactivedemo.services.SongService;
import io.lpamintuan.springreactivedemo.services.SongServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class SongControllerTest {

    private WebTestClient testClient;

    @Mock
    private SongService songService;

    @BeforeEach
    public void setUp() {
        this.testClient = WebTestClient.bindToController(new SongController(songService))
            .controllerAdvice(new ApiControllerAdvice()).build();
    }

    @Test
    public void getSongs_ReturnsAllSongsListed() {

        Flux<Song> expectedResults = Flux.just(
            new Song(),
            new Song()
        );
        Mockito.when(songService.getAllSongs()).thenReturn(expectedResults);
        
        testClient.get().uri("/songs")
            .exchange()
            .expectStatus().isCreated()
            .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$", hasSize(2));

    }

    @Test
    public void getSong_ReturnsASpecificSongUsingIdVariable_ifThereIsOne() {
        
        UUID id = UUID.randomUUID();

        Mono<Song> expectedResult = Mono.just(new Song(id.toString(), "Test Song #1"));
        Mockito.when(songService.getSong(any(String.class))).thenReturn(expectedResult);

        testClient.get().uri("/songs/" + id)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Song.class)
                .isEqualTo(new Song(id.toString(), "Test Song #1"));

    }

    @Test
    public void getSong_Throws404NotFound_ifThereIsNoSongsWithIdVariable() {
        UUID id = UUID.randomUUID();

        Mockito.when(songService.getSong(any(String.class))).thenReturn(
            Mono.error(EntityNotFoundException.getInstance(id.toString(), SongServiceImpl.ERROR_MESSAGE))
        );

        testClient.get().uri("/songs/" + id)
            .exchange()
            .expectStatus().isNotFound()
            .expectBody()
                .jsonPath("$.status").isEqualTo(404)
                .jsonPath("$.message").isNotEmpty();
    }


}
