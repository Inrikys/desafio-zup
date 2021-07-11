package com.zup.comics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.comics.entities.Creator;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

}

