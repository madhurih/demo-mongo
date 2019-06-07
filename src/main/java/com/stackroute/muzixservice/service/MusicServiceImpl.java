package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "music")
@Component
@Service
public class MusicServiceImpl implements MusicService
{
    MusicRepository musicRepository;

    public void simulateDelay() {
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Override
    public Music saveTrack(Music music)throws TrackAlreadyExistsException {
        if(musicRepository.existsById(music.getId()))
        {
            throw new TrackAlreadyExistsException("User Already Exists");
        }
        Music savedTrack= (Music) musicRepository.save(music);
        if(savedTrack==null)
        {
            throw new TrackAlreadyExistsException("User Already Exists");
        }
        return savedTrack;
    }

    @Override
    @Cacheable
    public List<Music> displayTrack() throws TrackNotFoundException{
        return musicRepository.findAll();
    }

    @Override
    public Music trackById(int trackId) throws TrackNotFoundException{
        Music music=null;
        Optional optional=musicRepository.findById(trackId);
        if(optional.isPresent())
        {
            music = (Music) musicRepository.findById(trackId).get();
        }
        return music;
    }

    @Override
    @CachePut
    public Music updateTrackComments(Music music, int id) throws TrackNotFoundException {
        Music musicList=null;
        Optional optional=musicRepository.findById(id);
        if(optional.isPresent())
        {
            musicList = (Music) musicRepository.findById(id).get();
            musicList.setTrackComments(music.getTrackComments());
        }
        return musicList;
    }

    @Override
    public Music removeTrack(int trackId)throws TrackNotFoundException{
        Music music=null;
        Optional optional=musicRepository.findById(trackId);
        if(optional.isPresent())
        {
            music = (Music) musicRepository.findById(trackId).get();
            musicRepository.deleteById(trackId);
        }
        return music;
    }

    @PostConstruct
    public void loadData()
    {
        musicRepository.save(Music.builder().id(4).trackName("Unse mili najar").trackComments("sweet song").build());
        musicRepository.save(Music.builder().id(5).trackName("senorita maria").trackComments("love song").build());
    }
}