package com.zup.comics.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.comics.dto.CreatorDTO;
import com.zup.comics.entities.Creator;
import com.zup.comics.repositories.CreatorRepository;
import com.zup.comics.services.exceptions.ResourceNotFoundException;

@Service
public class CreatorService {

	@Autowired
	private CreatorRepository repository;

	public List<CreatorDTO> findAll() {
		List<Creator> result = repository.findAll();
		return result.stream().map(x -> new CreatorDTO(x)).collect(Collectors.toList());
	}
	
	public CreatorDTO findById(Long id) {
		try {
			Optional<Creator> obj = repository.findById(id);
			CreatorDTO result = new CreatorDTO(obj.get());
			return result;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

}

