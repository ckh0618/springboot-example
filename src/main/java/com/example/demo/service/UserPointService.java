package com.example.demo.service;

import com.example.demo.model.UserPoint;
import com.example.demo.repository.UserPointRepository;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserPointService {
    private final UserPointRepository userPointRepository;
    private final MongoTemplate mongoTemplate;


    public UserPointService(UserPointRepository userPointRepository, MongoTemplate template) {
        this.userPointRepository = userPointRepository;
        this.mongoTemplate = template ;
    }

    public UserPoint getUserPoint (int userId ) {
        return userPointRepository.findByUserIdAndIsActive(userId, true);
    }

    public int generate() {

        mongoTemplate.dropCollection(UserPoint.class);

        List<UserPoint> userPoints = new ArrayList<>();

        BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, UserPoint.class);

        for ( int i = 0 ; i < 100000; i ++ ) {
            UserPoint userPoint = new UserPoint(i, "User" + i , true, 1000);
            bulkOps.insert(userPoint);
        }

        return bulkOps.execute().getInsertedCount();
    }

    public UserPoint findByUserId(int userId) {
        return userPointRepository.findByUserId(userId);
    }

    public UserPoint setActivate(int userId, boolean isActive) {
        UserPoint userPoint = userPointRepository.findByUserId(userId);
        userPoint.setIsActive(isActive);
        return userPointRepository.save(userPoint);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserPoint transfer ( int fromUserId, int toUserId, int point ) {
        UserPoint fromUser = userPointRepository.findByUserId(fromUserId);
        UserPoint toUser = userPointRepository.findByUserId(toUserId);

        fromUser.setPoint(fromUser.getPoint() - point);
        toUser.setPoint(toUser.getPoint() + point);

        userPointRepository.save(fromUser);
        userPointRepository.save(toUser);

        return fromUser;
    }
}
