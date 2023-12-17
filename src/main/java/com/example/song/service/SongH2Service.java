package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.song.model.Song;
import com.example.song.repository.SongRepository;
import com.example.song.model.SongRowMapper;

@Service
public class SongH2Service implements SongRepository {
    @Autowired
    JdbcTemplate db;

    @Override
    public ArrayList<Song> getSongs() {
        List<Song> songsList = db.query("SELECT * FROM PLAYLIST", new SongRowMapper());
        ArrayList<Song> songs = new ArrayList<>(songsList);
        return songs;
    }

    @Override
    public Song getSongById(int songId) {
        try {
            Song song = db.queryForObject("SELECT * FROM PLAYLIST WHERE songId = ?", new SongRowMapper(), songId);
            return song;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song addNewSong(Song song){
        db.update("INSERT INTO PLAYLIST(songName, lyricist, singer, musicDirector) values (?,?,?,?)", song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());
        Song savedSong = db.queryForObject("SELECT * FROM PLAYLIST WHERE songName = ? and lyricist = ? and singer = ? and musicDirector = ?", new SongRowMapper(), song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());
        return savedSong;
    }

    @Override
    public Song updateSong(int songId, Song song){
        if (song.getSongName() != null){
            db.update("UPDATE PLAYLIST SET songName = ? WHERE songId = ?", song.getSongName(), songId);
        }
        if (song.getLyricist() != null){
            db.update("UPDATE PLAYLIST SET lyricist = ? WHERE songId = ?", song.getLyricist(), songId);
        }
        if (song.getSinger() != null){
            db.update("UPDATE PLAYLIST SET singer = ? WHERE songId = ?", song.getSinger(), songId);
        }
        if (song.getMusicDirector() != null){
            db.update("UPDATE PLAYLIST SET musicDirector = ? WHERE songId = ?", song.getMusicDirector(), songId);
        }
        return getSongById(songId);

    }

    @Override
    public void deleteSong(int songId){
        db.update("DELETE FROM PLAYLIST WHERE songId = ?",  songId);
    }

}
