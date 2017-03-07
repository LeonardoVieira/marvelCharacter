/**
 * 
 */
package com.marvel.character.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.marvel.character.exception.MarvelException;
import com.marvel.character.model.Comic;
import com.marvel.character.model.MarvelCharacter;
import com.marvel.character.model.Result;
import com.marvel.character.model.User;
import com.marvel.character.repository.MarvelCharacterRepository;
import com.marvel.character.service.MarvelCharacterService;
import com.marvel.character.util.parameters.CharacterParameterBuilder;
import com.marvel.character.util.parameters.ComicParametersBuilder;
import com.marvel.character.web.RestClient;

/**
 * @author leonardo
 *
 */
@Service
public class MarvelCharacterServiceImpl implements MarvelCharacterService {

	@Autowired
	private MarvelCharacterRepository marvelCharacterRepository;

	/*
	 * (non-Javadoc)
	 * @see com.digital.pages.marvel.service.ManufactureService#findAll()
	 */
	@Override
	public List<MarvelCharacter> findAll() {
		return marvelCharacterRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * @see com.digital.pages.marvel.service.MarvelCharacterService#save(java.util.List)
	 */
	@Override
	public void save(List<MarvelCharacter> marvelCharacterList) {
		marvelCharacterRepository.save(marvelCharacterList);
	}

	@Override
	@Cacheable("characters")
	public void downloadCharacterProfile(User user) throws MarvelException {
		try {
			List<MarvelCharacter> list = new ArrayList<>();
			RestClient client = createRestClient(user);

			for(char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
				Result<MarvelCharacter> characters = client.getCharacters(new CharacterParameterBuilder().nameStartsWith(String.valueOf(alphabet)).create());
				list.addAll(characters.getData().getResults());
			}

			save(list);
		} catch (IOException e) {
			throw new MarvelException("Não foi possivel baixar os dados dos personagens");
		}
	}

	private RestClient createRestClient(User user) {
		return new RestClient(user.getPrivateKey(), user.getPublicKey());
	}

	@Override
	@Cacheable("character")
	public MarvelCharacter findById(Integer id) {
		return marvelCharacterRepository.findOne(id);
	}

	@Override
	@Cacheable("profile")
	public Result<Comic> findComicsByCharacterId(Integer id, User user) throws MarvelException {
		try {
			return createRestClient(user).getCharactersComics(new ComicParametersBuilder(id).create());
		} catch (IOException e) {
			throw new MarvelException("Não foi possivel recuperar os dados dos HQ's dos personagens");
		}
	}
}