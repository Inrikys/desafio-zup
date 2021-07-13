package com.zup.comics.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.zup.comics.entities.Comic;
import com.zup.comics.entities.User;
import com.zup.comics.feign.MarvelClient;
import com.zup.comics.feign.dto.marvel.MarvelComicResponse;
import com.zup.comics.feign.dto.marvel.Price;
import com.zup.comics.feign.dto.marvel.Result;
import com.zup.comics.repositories.ComicRepository;
import com.zup.comics.repositories.UserRepository;
import com.zup.comics.services.exceptions.DatabaseException;
import com.zup.comics.services.exceptions.ResourceNotFoundException;

import feign.FeignException;

@Service
public class ComicService {

	@Autowired
	private ComicRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MarvelClient marvelApi;

	private final String APIKEY = "85c27bd1a7f22584b5eacd853bf278fd";
	private final String TS = "zupper";
	private final String HASH = "a444cce1b354739736b7c46516ed63aa";

	public List<Comic> findAll() {
		return repository.findAll();
	}

	public Comic findById(Long id) {
		Optional<Comic> obj = repository.findById(id);
		return obj.get();
	}

	public MarvelComicResponse findByIdFromMarvelApi(Long id) {
		try {
			return marvelApi.getComicById(id, TS, APIKEY, HASH);
		} catch (FeignException e) {
			throw e;
		}
	}

	public User RegisterComicFromMarvelApi(Long comicId, Long userId) {
		try {
			MarvelComicResponse marvelComicResponse = marvelApi.getComicById(comicId, TS, APIKEY, HASH);

			// Resgata dados da Marvel API
			Comic comicObj = new Comic();
			for (Result x : marvelComicResponse.getData().getResults()) {
				comicObj.setId(Long.valueOf(x.getId()));
				comicObj.setName(x.getTitle());
				comicObj.setDescription(x.getDescription());
				comicObj.setIsbn(x.getIsbn());

				for (Price p : x.getPrices()) {
					comicObj.setPrice(p.getPrice());
				}
			}

			// Vincula com o usuário
			Optional<User> userOptional = userRepository.findById(userId);
			User userObj = userOptional.get();
			userObj.getComics().add(comicObj);

			// Salva dados
			repository.save(comicObj);
			return userRepository.save(userObj);

		} catch (FeignException e) {
			throw e;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(userId);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
