package com.joyscout.NonGrata.service;

import com.joyscout.NonGrata.model.User;
import com.joyscout.NonGrata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class UserService implements UserRepository {

    private final String USER_CACHE = "USERS";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, User> hashOperations;

    @PostConstruct
    private void initializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void ban(final User user) {
        hashOperations.put(USER_CACHE, user.getId(), user);
    }

    @Override
    public Boolean isBanned(final String id) {
        return hashOperations.hasKey(USER_CACHE, id);
    }

    @Override
    public Map<String, User> findAll() {
        return hashOperations.entries(USER_CACHE);
    }

    @Override
    public void unban(String id) {
        hashOperations.delete(USER_CACHE, id);
    }
}
