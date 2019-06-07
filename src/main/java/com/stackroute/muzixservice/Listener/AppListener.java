package com.stackroute.muzixservice.Listener;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent>
{
    Music music=new Music();

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        music.setId(Integer.parseInt(environment.getProperty("music2.id")));
        music.setTrackName(environment.getProperty("music2.trackName"));
        music.setTrackComments(environment.getProperty("music2.trackComments"));
        musicRepository.save(music);
    }
}
