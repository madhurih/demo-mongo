package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends MongoRepository<Music, Integer>
{
}