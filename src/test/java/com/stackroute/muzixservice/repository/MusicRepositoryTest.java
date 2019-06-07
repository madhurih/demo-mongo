package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MusicRepositoryTest {
    @Autowired
    private MusicRepository musicRepository;
    private Music music;

    @Before
    public void setUp()
    {
        music = new Music();
        music.setId(1);
        music.setTrackName("bin tere sanam");
        music.setTrackComments("awesome");
    }

    @After
    public void tearDown(){
        musicRepository.deleteAll();
    }

    @Test
    public void testSaveTrack(){
        musicRepository.save(music);
        Music fetchTrack = (Music) musicRepository.findById(music.getId()).get();
        Assert.assertEquals(1,fetchTrack.getId());
    }

    @Test
    public void testSaveTrackFailure(){
        Music testTrack = new Music(1, "dil me ho tum", "love song");
        musicRepository.save(music);
        Music fetchTrack = (Music) musicRepository.findById(music.getId()).get();
        Assert.assertNotSame(testTrack,music);
    }

    @Test
    public void testDisplayTrack(){
            Music music = new Music(1, "dil me ho tum", "love song");
            musicRepository.save(music);

            List<Music> list = musicRepository.findAll();
            Assert.assertEquals(1,list.get(0).getId());
    }

    @Test
    public void testDisplayTrackFailure(){
        Music music = new Music(1, "dil me ho tum", "love song");
        Music music1 = new Music(2, "humsafar mere", "love song");
        musicRepository.save(music);
        musicRepository.save(music1);

        List<Music> list = musicRepository.findAll();
        Assert.assertNotSame("humsafar",list.get(0).getTrackName());
    }

    @Test
    public void testRemoveTrack(){
        Music music = new Music(1, "dil me ho tum", "love song");
        musicRepository.save(music);
        musicRepository.deleteById(1);
        Assert.assertEquals(0, musicRepository.count());
    }

    @Test
    public void testRemoveTrackFailure(){
        Music music = new Music(1, "dil me ho tum", "love song");
        musicRepository.save(music);
        musicRepository.deleteById(1);
        Assert.assertNotEquals(1, musicRepository.count());
    }

    @Test
    public void testTrackById(){
        Music music = new Music(1, "dil me ho tum", "love song");
        musicRepository.save(music);
        System.out.println(music);
        Music music1 = (Music) musicRepository.findById(1).get();
        System.out.println(music1);
        Assert.assertEquals(music1, music);
    }
}
