package com.zup.comics.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.zup.comics.entities.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private String cpf;
	private Instant dob;

	private Set<ComicDTO> comics;

	public UserDTO() {

	}

	public UserDTO(Long id, String name, String email, String cpf, Instant dob, Set<ComicDTO> comics) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.dob = dob;
		this.comics = comics;
	}

	public UserDTO(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.cpf = entity.getCpf();
		this.dob = entity.getDob();

		if (entity.getComics() != null) {
			this.comics = entity.getComics().stream().map(x -> new ComicDTO(x)).collect(Collectors.toSet());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Instant getDob() {
		return dob;
	}

	public void setDob(Instant dob) {
		this.dob = dob;
	}

	public Set<ComicDTO> getComics() {
		return comics;
	}
}
