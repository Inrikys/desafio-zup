package com.zup.comics.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.comics.dto.ComicDTO;
import com.zup.comics.dto.UserDTO;
import com.zup.comics.feign.dto.marvel.MarvelComicResponse;
import com.zup.comics.services.ComicService;

@RestController
@RequestMapping(value = "/comics")
public class ComicResource {

	@Autowired
	private ComicService service;

	@GetMapping
	public ResponseEntity<List<ComicDTO>> findAll() {
		List<ComicDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ComicDTO> findById(@PathVariable Long id) {
		ComicDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/marvel/{id}")
	public ResponseEntity<MarvelComicResponse> findByIdFromMarvelApi(@PathVariable Long id) {
		MarvelComicResponse obj = service.findByIdFromMarvelApi(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/register/{comicId}/{userId}")
	public ResponseEntity<UserDTO> registerComicFromMarvelApi(@PathVariable Long comicId, @PathVariable Long userId) {
		UserDTO obj = service.registerComicFromMarvelApi(comicId, userId);
		return ResponseEntity.ok().body(obj);
	}

}
