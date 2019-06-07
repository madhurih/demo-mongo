package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MusicRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MusicServiceTest {
    private Music music;

    //Create a mock for UserRepository
    @Mock
    private MusicRepository musicRepository;
    Optional optional;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private MusicServiceImpl musicService;
    List<Music> list= null;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        music = new Music();
        music.setId(1);
        music.setTrackName("pal pal dil ke paas");
        music.setTrackComments("Love song");
        list = new ArrayList<>();
        list.add(music);
        optional= Optional.of(music);
    }
    @After
    public void teardown()
    {
        music=null;
        optional=null;
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {
        when(musicRepository.save((Music) any())).thenReturn(music);
        Music savedTrack = musicService.saveTrack(music);
        Assert.assertEquals(music,savedTrack);
        //verify here verifies that musicRepository save method is only called once
        verify(musicRepository,times(1)).save(music);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(musicRepository.save((Music) any())).thenReturn(null);
        Music savedTrack = musicService.saveTrack(music);
        System.out.println("savedTrack" + savedTrack);
        Assert.assertNotEquals(music,savedTrack);
    }

    @Test
    public void displayTrackTestSuccess() throws TrackNotFoundException {
        musicRepository.save(music);
        //stubbing the mock to return specific data
        when(musicRepository.findAll()).thenReturn(list);
        List<Music> musicList = musicService.displayTrack();
        Assert.assertEquals(list,musicList);
    }

    @Test()
    public void updateTrackTest() throws TrackNotFoundException {
        when(musicRepository.findById(music.getId())).thenReturn(optional);
        Music music1=new Music(music.getId(),music.getTrackName(),"awesome");
        Music savedTrack = musicService.updateTrackComments(music1,music.getId());
        Assert.assertEquals(music1,savedTrack);
    }

    @Test
    public void deleteTrackTest() throws TrackNotFoundException {
        when(musicRepository.findById(music.getId())).thenReturn(optional);
        Music deletedtrack=musicService.removeTrack(music.getId());
        Assert.assertEquals(1,deletedtrack.getId());
        verify(musicRepository,times(1)).deleteById(music.getId());
    }

    @Test
    public void trackByIdTestSuccess() throws TrackNotFoundException {
        when(musicRepository.findById(music.getId())).thenReturn(optional);
        Music savedUser = musicService.trackById(music.getId());
        Assert.assertEquals(1,savedUser.getId());
    }
}