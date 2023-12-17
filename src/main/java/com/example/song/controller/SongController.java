package com.example.song.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.song.model.Song;
import com.example.song.service.SongH2Service;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SongController {
    @Autowired
    SongH2Service songH2Service;

    @GetMapping("/songs")
    public ArrayList<Song> getSongs() {
        return songH2Service.getSongs();
    }

    @GetMapping("/songs/{songId}")
    public Song getSongById(@PathVariable("songId") int songId) {
        return songH2Service.getSongById(songId);

    }

    @PostMapping("/songs")
    public Song addNewSong(@RequestBody Song song) {
        return songH2Service.addNewSong(song);
    }

    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable("songId") int songId, @RequestBody Song song) {
        return songH2Service.updateSong(songId, song);

    }

    @DeleteMapping("/songs/{songId}")
    public void deleteSong(@PathVariable int songId) {
        songH2Service.deleteSong(songId);
    }

}
