package io.lpamintuan.springreactivedemo.repositories;

import java.util.UUID;

import io.lpamintuan.springreactivedemo.models.Song;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SongRepository {

    Flux<Song> findAll();

    Mono<Song> findById(UUID any);
    
}
