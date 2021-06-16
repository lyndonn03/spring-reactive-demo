package io.lpamintuan.springreactivedemo.services;

import static org.mockito.ArgumentMatchers.any;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.lpamintuan.springreactivedemo.models.Song;
import io.lpamintuan.springreactivedemo.repositories.SongRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class SongServiceImplTest {
    
    @Mock
    private SongRepository songRepository;

    private SongService songService;

    @BeforeEach
    public void setUp() {
        this.songService = new SongServiceImpl(songRepository);
    }

    @Test
    public void getAllSongs_ShouldReturnFluxOfSongs() {
        Flux<Song> expectedResult = Flux.just(
            new Song(null, "Test Song #1"),
            new Song(null, "Test Song #2")
        );
        Mockito.when(songRepository.findAll()).thenReturn(expectedResult);

        Flux<Song> actualResult = songService.getAllSongs();

        StepVerifier.create(actualResult)
            .expectNextMatches(song -> song.getName().equals("Test Song #1"))
            .expectNextMatches(song -> song.getName().equals("Test Song #2"))
            .verifyComplete();

    }

    @Test
    public void getSong_ThrowsEntityNotFoundException_ifSongWithIdVariableNotFound() {
        UUID id = UUID.randomUUID();
        Mockito.when(songRepository.findById(any(UUID.class)))
            .thenReturn(Mono.empty());
            
        Mono<Song> expectedResult = songService.getSong(id);
        
        StepVerifier.create(expectedResult)
            .expectErrorMessage(String.format(SongServiceImpl.ERROR_MESSAGE, id.toString()))
            .verify();
    }

}
