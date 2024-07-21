package com.example.demo.repository;

import com.example.demo.model.UserPoint;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserPointRepository extends CrudRepository<UserPoint, Integer> {


    UserPoint findByUserId (int userId );

    UserPoint findByUserIdAndIsActive(int userId, boolean isActive);


}
