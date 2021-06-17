package io.lpamintuan.springreactivedemo.configurations;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import io.lpamintuan.springreactivedemo.models.Song;
import io.lpamintuan.springreactivedemo.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class InitiaDataPopulator {

    private final SongRepository songRepository;

    @PostConstruct
    public void initSongData() {
        log.info("Initializing song data");
        Flux<Song> songs = Flux.just(
            new Song("Breakeven"),
            new Song("Man Who can't be Move")
        );
        songRepository.deleteAll()
            .thenMany(songs)
            .flatMap(songRepository::save).subscribe(r -> {
                log.info("SONG: " + r.getName());
             });
        
        log.info("Song data initialized");
    }
    
}
