package com.tomato.userlogin.repository;

import com.tomato.userlogin.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserData, Long> {

    Optional<UserData> findByEmail(String email);
}
