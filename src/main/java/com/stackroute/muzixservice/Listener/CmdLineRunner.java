package com.stackroute.muzixservice.Listener;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class CmdLineRunner implements CommandLineRunner
{
        @Value("${music1.id}")
        private int id;
        @Value("${music1.trackName}")
        private String trackName;
        @Value("${music1.trackComments}")
        private String trackComments;

        Music music=new Music();

        @Autowired
        MusicRepository musicRepository;

        @Override
        public void run(String... args) throws Exception {
            music.setId(id);
            music.setTrackName(trackName);
            music.setTrackComments(trackComments);
            musicRepository.save(music);
        }
}
