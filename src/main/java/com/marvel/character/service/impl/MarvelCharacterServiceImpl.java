/**
 * 
 */
package com.marvel.character.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.marvel.character.exception.MarvelException;
import com.marvel.character.model.Comic;
import com.marvel.character.model.MarvelCharacter;
import com.marvel.character.model.Result;
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

	private static final Logger LOGGER = Logger.getLogger(MarvelCharacterServiceImpl.class.getName());

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
	public void downloadCharacterProfile(String privateKey, String publicKey) throws MarvelException {
		try {
			RestClient client = new RestClient(privateKey, publicKey);
			List<MarvelCharacter> list = new ArrayList<>();

			downloadCharacterProfile(client, list, 0);

//			for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
//				Result<MarvelCharacter> characters = client.getCharacters(new CharacterParameterBuilder().nameStartsWith(String.valueOf(alphabet)).create());
//				list.addAll(characters.getData().getResults());
//			}

			save(list);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Não foi possivel baixar os dados dos personagens");
		}
	}

	public void downloadCharacterProfile(RestClient client, List<MarvelCharacter> list, Integer offset) throws IOException {
		Result<MarvelCharacter> characters = client.getCharacters(new CharacterParameterBuilder().withOffset(offset).withLimit(100).create());
		list.addAll(characters.getData().getResults());

		if(characters.getData().getTotal() > list.size()) {
			downloadCharacterProfile(client, list, offset + 100);
		}
	}

	@Override
	@Cacheable("character")
	public MarvelCharacter findById(Integer id) {
		return marvelCharacterRepository.findOne(id);
	}

	@Override
	@Cacheable("profile")
	public Result<Comic> findComicsByCharacterId(Integer id, String privateKey, String publicKey) throws MarvelException {
		try {
			RestClient client = new RestClient(privateKey, publicKey);
			return client.getCharactersComics(new ComicParametersBuilder(id).create());
		} catch (IOException e) {
			throw new MarvelException("Não foi possivel recuperar os dados dos HQ's dos personagens");
		}
	}
}