package com.zup.comics.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "marvelApi", url = "http://gateway.marvel.com/v1/public/")
public interface MarvelClient {

	@GetMapping("/comics/{id}")
	public String getComicById(@RequestParam(value = "ts") String ts, @RequestParam(value = "apikey") String apikey,
			@RequestParam(value = "hash") String hash);

}
