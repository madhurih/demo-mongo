package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MusicController {
    private MusicService musicService;
    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }
    @PostMapping("/tracks")
    public ResponseEntity<?> saveTrack(@RequestBody Music music) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        try{
            musicService.saveTrack(music);
            responseEntity=new ResponseEntity<String>("Successfully saved", HttpStatus.CREATED);
        }catch(Exception exception)
        {
            responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/tracks")
    public ResponseEntity<?> displayTracks() throws TrackNotFoundException {
        List<Music> musicList=musicService.displayTrack();
        return new ResponseEntity<List<Music>>(musicList,HttpStatus.OK);
    }

    @DeleteMapping("/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id) throws TrackNotFoundException {
        musicService.removeTrack(id);
        return new ResponseEntity<String>("Record Deleted",HttpStatus.CREATED);
    }

    @PutMapping("/track/{id}")
    public ResponseEntity<?> updateTracks(@RequestBody Music music,@PathVariable int id) throws TrackNotFoundException {
        Music musicList=musicService.updateTrackComments(music,id);
        return new ResponseEntity<Music>(musicList,HttpStatus.CREATED);
    }

    @GetMapping("/track/{id}")
    public ResponseEntity<?> tracksById(@PathVariable int id) throws TrackNotFoundException {
        Music musicList = musicService.trackById(id);
        return new ResponseEntity<Music>(musicList,HttpStatus.CREATED);
    }
}