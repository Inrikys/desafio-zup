package com.zup.comics.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.comics.entities.Creator;
import com.zup.comics.repositories.CreatorRepository;

@Service
public class CreatorService {

	@Autowired
	private CreatorRepository repository;

	public List<Creator> findAll() {
		return repository.findAll();
	}
	
	public Creator findById(Long id) {
		Optional<Creator> obj = repository.findById(id);
		
		return obj.get();
	}

}

