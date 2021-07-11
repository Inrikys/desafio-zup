package com.zup.comics.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.comics.entities.Comic;
import com.zup.comics.services.ComicService;

@RestController
@RequestMapping(value = "/comics")
public class ComicResource {
	
	@Autowired
	private ComicService service;
	
	@GetMapping
	public ResponseEntity<List<Comic>> findAll() {
		List<Comic> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Comic> findById(@PathVariable Long id){
		Comic obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
