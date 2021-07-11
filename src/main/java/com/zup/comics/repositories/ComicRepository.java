package com.zup.comics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.comics.entities.Comic;

public interface ComicRepository extends JpaRepository<Comic, Long> {

}

