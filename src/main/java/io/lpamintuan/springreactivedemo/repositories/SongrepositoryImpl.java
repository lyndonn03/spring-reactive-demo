package io.lpamintuan.springreactivedemo.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import io.lpamintuan.springreactivedemo.models.Song;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class SongrepositoryImpl implements SongRepository {

    @Override
    public Flux<Song> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Song> findById(UUID any) {
        // TODO Auto-generated method stub
        return Mono.empty();
    }
    
}
