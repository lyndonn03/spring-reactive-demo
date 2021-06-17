package io.lpamintuan.springreactivedemo.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import io.lpamintuan.springreactivedemo.models.Song;

@Repository
public interface SongRepository extends ReactiveCrudRepository<Song, String> {

}
