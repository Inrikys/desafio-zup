package com.zup.comics.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.comics.dto.CreatorDTO;
import com.zup.comics.services.CreatorService;

@RestController
@RequestMapping(value = "/creators")
public class CreatorResource {
	
	@Autowired
	private CreatorService service;
	
	@GetMapping
	public ResponseEntity<List<CreatorDTO>> findAll() {
		List<CreatorDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CreatorDTO> findById(@PathVariable Long id){
		CreatorDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
