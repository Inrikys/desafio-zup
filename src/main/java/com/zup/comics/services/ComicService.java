package com.zup.comics.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.zup.comics.dto.ComicDTO;
import com.zup.comics.dto.UserDTO;
import com.zup.comics.entities.Comic;
import com.zup.comics.entities.Creator;
import com.zup.comics.entities.User;
import com.zup.comics.feign.MarvelClient;
import com.zup.comics.feign.dto.marvel.Item;
import com.zup.comics.feign.dto.marvel.MarvelComicResponse;
import com.zup.comics.feign.dto.marvel.Price;
import com.zup.comics.feign.dto.marvel.Result;
import com.zup.comics.repositories.ComicRepository;
import com.zup.comics.repositories.CreatorRepository;
import com.zup.comics.repositories.UserRepository;
import com.zup.comics.services.exceptions.DatabaseException;
import com.zup.comics.services.exceptions.ResourceNotFoundException;

import feign.FeignException;

@Service
public class ComicService {

	@Autowired
	private ComicRepository comicRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CreatorRepository creatorRepository;

	@Autowired
	private MarvelClient marvelApi;

	private final String APIKEY = "85c27bd1a7f22584b5eacd853bf278fd";
	private final String TS = "zupper";
	private final String HASH = "a444cce1b354739736b7c46516ed63aa";

	public List<ComicDTO> findAll() {
		List<Comic> result = comicRepository.findAll();
		return result.stream().map(x -> new ComicDTO(x)).collect(Collectors.toList());
	}

	public ComicDTO findById(Long id) {
		try {
		Optional<Comic> obj = comicRepository.findById(id);
		ComicDTO result = new ComicDTO(obj.get());
		return result;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public MarvelComicResponse findByIdFromMarvelApi(Long id) {
		try {
			return marvelApi.getComicById(id, TS, APIKEY, HASH);
		} catch (FeignException e) {
			throw e;
		}
	}

	public UserDTO registerComicFromMarvelApi(Long comicId, Long userId) {
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

				// Dados dos autores da Comic
				for (Item i : x.getCreators().getItems()) {
					Creator creator = new Creator();

					// Pegar ID do autor pela URL
					int index = i.getResourceURI().lastIndexOf("/");
					Long creatorId = Long.parseLong(i.getResourceURI().substring(index + 1));

					// Salva informações necessárias
					creator.setId(creatorId);
					creator.setName(i.getName());
					creator.setRole(i.getRole());

					comicObj.getCreators().add(creator);
				}

			}

			creatorRepository.saveAll(comicObj.getCreators());
			comicRepository.save(comicObj);

			// Vincula Comic com o usuário
			Optional<User> userOptional = userRepository.findById(userId);
			User userObj = userOptional.get();
			userObj.getComics().add(comicObj);
				
			User userResult = userRepository.save(userObj);
			UserDTO result = new UserDTO(userResult);
			return result;

		} catch (FeignException e) {
			throw e;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(userId);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
