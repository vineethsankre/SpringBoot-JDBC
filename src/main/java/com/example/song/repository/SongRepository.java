package com.example.song.repository;

import com.example.song.model.Song;
import java.util.*;

public interface SongRepository {
    ArrayList<Song> getSongs();

    Song getSongById(int songId);

    Song addNewSong(Song song);

    Song updateSong(int songId, Song song);

    void deleteSong(int songId);
}