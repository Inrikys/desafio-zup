package com.zup.comics.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.zup.comics.feign.dto.marvel.MarvelComicResponse;

@FeignClient(name = "marvelApi", url = "http://gateway.marvel.com/v1/public/")
public interface MarvelClient {

	@GetMapping("/comics/{id}")
	public MarvelComicResponse getComicById(@PathVariable("id") Long id, @RequestParam(value = "ts") String ts,
			@RequestParam(value = "apikey") String apikey, @RequestParam(value = "hash") String hash);
}
