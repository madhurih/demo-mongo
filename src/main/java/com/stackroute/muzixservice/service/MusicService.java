package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import java.util.List;

public interface MusicService
{
    public Music saveTrack(Music music) throws TrackAlreadyExistsException;
    public List<Music>displayTrack() throws TrackNotFoundException;
    public Music trackById(int trackId) throws TrackNotFoundException;
    public Music updateTrackComments(Music music, int trackId) throws TrackNotFoundException;
    public Music removeTrack(int trackId) throws TrackNotFoundException;

}