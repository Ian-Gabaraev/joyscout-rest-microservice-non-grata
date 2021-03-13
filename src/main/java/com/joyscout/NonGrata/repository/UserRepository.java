package com.joyscout.NonGrata.repository;

import com.joyscout.NonGrata.model.User;

import java.util.Map;

public interface UserRepository {

    void ban(User user);

    Boolean isBanned(String id);

    Map<String, User> findAll();

    void unban(String  id);
}
