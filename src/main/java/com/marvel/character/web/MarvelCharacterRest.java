package com.marvel.character.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.character.model.MarvelCharacter;
import com.marvel.character.service.MarvelCharacterService;

@RestController
public class MarvelCharacterRest {

	@Autowired
	private MarvelCharacterService marvelCharacterService;

	@GetMapping("/marvelCharacter/rest")
	public @ResponseBody List<MarvelCharacter> findAll() {
		return marvelCharacterService.findAll();
	}

	@GetMapping("/marvelCharacter/rest/{id}")
	public @ResponseBody MarvelCharacter getById(@PathVariable(value = "id") Integer id) {
		return marvelCharacterService.findById(id);
	}

	@PostMapping("/marvelCharacter/rest")
	public @ResponseBody MarvelCharacter save(@RequestBody MarvelCharacter marvelCharacter) {
		return marvelCharacterService.save(marvelCharacter);
	}

	@PutMapping("/marvelCharacter/rest")
	public @ResponseBody MarvelCharacter update(@RequestBody MarvelCharacter marvelCharacter) {
		return marvelCharacterService.update(marvelCharacter);
	}

	@DeleteMapping("/marvelCharacter/rest/{id}")
	public @ResponseBody List<MarvelCharacter> delete(@PathVariable(value = "id") Integer id) {
		marvelCharacterService.delete(id);
		//TODO ARRUMAR ISSO
		return null;
	}
}