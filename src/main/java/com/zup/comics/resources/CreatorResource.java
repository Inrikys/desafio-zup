package com.zup.comics.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.comics.entities.Creator;
import com.zup.comics.services.CreatorService;

@RestController
@RequestMapping(value = "/Creators")
public class CreatorResource {
	
	@Autowired
	private CreatorService service;
	
	@GetMapping
	public ResponseEntity<List<Creator>> findAll() {
		List<Creator> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Creator> findById(@PathVariable Long id){
		Creator obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
