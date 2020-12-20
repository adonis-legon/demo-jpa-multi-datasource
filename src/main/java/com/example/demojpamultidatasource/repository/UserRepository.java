package com.example.demojpamultidatasource.repository;

import java.util.Optional;

import com.example.demojpamultidatasource.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
