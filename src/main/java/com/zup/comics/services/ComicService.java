package com.zup.comics.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.comics.entities.Comic;
import com.zup.comics.repositories.ComicRepository;

@Service
public class ComicService {

	@Autowired
	private ComicRepository repository;

	public List<Comic> findAll() {
		return repository.findAll();
	}
	
	public Comic findById(Long id) {
		Optional<Comic> obj = repository.findById(id);
		
		return obj.get();
	}

}

