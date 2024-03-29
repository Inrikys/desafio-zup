package com.zup.comics.services;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.zup.comics.dto.UserDTO;
import com.zup.comics.entities.User;
import com.zup.comics.repositories.UserRepository;
import com.zup.comics.services.exceptions.DatabaseException;
import com.zup.comics.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<UserDTO> findAll() {
		List<User> result = repository.findAll();
		return result.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}

	public UserDTO findById(Long id) {
		try {
			Optional<User> obj = repository.findById(id);
			UserDTO result = new UserDTO(obj.get());
			return result;

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	public UserDTO insert(User obj) {
		try {
			User userResult = repository.save(obj);
			UserDTO result = new UserDTO(userResult);
			return result;
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (ConstraintViolationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NullPointerException e) {
			throw e;
		}
	}

	public UserDTO update(Long id, User obj) {
		try {
			Optional<User> entity = repository.findById(id);
			updateData(entity.get(), obj);
			User userResult = repository.save(entity.get());
			UserDTO result = new UserDTO(userResult);
			return result;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NullPointerException e) {
			throw e;
		}catch (DateTimeParseException e) {
			throw e;
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setDob(obj.getDob());
	}

}
