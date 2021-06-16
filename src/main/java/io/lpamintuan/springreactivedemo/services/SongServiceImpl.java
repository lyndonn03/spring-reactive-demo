package io.lpamintuan.springreactivedemo.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.lpamintuan.springreactivedemo.globals.EntityNotFoundException;
import io.lpamintuan.springreactivedemo.models.Song;
import io.lpamintuan.springreactivedemo.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public static final String ERROR_MESSAGE = "Song with id: %s doesn't exist.";

    @Override
    public Flux<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Mono<Song> getSong(UUID songId) {
        return Mono.just(songId)
                .flatMap(songRepository::findById)
                .switchIfEmpty(
                    Mono.error(EntityNotFoundException.getInstance(songId.toString(), SongServiceImpl.ERROR_MESSAGE))
                );
    }


}
