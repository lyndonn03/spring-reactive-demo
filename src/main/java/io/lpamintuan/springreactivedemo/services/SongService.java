package io.lpamintuan.springreactivedemo.services;

import java.util.UUID;

import io.lpamintuan.springreactivedemo.models.Song;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SongService {

    public Flux<Song> getAllSongs();

    public Mono<Song> getSong(UUID songId);
    
}
