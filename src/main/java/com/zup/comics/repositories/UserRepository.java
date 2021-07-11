package com.zup.comics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.comics.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

