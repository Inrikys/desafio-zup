package com.zup.comics.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import com.zup.comics.entities.Comic;
import com.zup.comics.feign.MarvelClient;
import com.zup.comics.feign.dto.marvel.MarvelComicResponse;
import com.zup.comics.repositories.ComicRepository;

import feign.FeignException;

@Service
@EnableFeignClients
public class ComicService {

	@Autowired
	private ComicRepository repository;

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

	public List<MarvelComicResponse> findAllFromMarvelApi() {
		try {
			return marvelApi.getComics(TS, APIKEY, HASH);
		} catch (FeignException e) {
			throw e;
		}

	}

}
