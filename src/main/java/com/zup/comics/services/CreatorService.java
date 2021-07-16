package com.zup.comics.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.comics.entities.Creator;
import com.zup.comics.repositories.CreatorRepository;
import com.zup.comics.services.exceptions.ResourceNotFoundException;

@Service
public class CreatorService {

	@Autowired
	private CreatorRepository repository;

	public List<Creator> findAll() {
		return repository.findAll();
	}
	
	public Creator findById(Long id) {
		try {
			Optional<Creator> obj = repository.findById(id);
			return obj.get();
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

}

