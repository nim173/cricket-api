package com.nimesh.cricket_api.jwt.repository;

import com.nimesh.cricket_api.jwt.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    public User findByUsername(String username);
}
