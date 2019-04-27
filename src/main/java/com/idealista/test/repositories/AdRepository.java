package com.idealista.test.repositories;

import com.idealista.test.models.Ad;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AdRepository extends MongoRepository<Ad, String> {
  Ad findById(Integer id);
}
