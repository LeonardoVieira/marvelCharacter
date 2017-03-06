/**
 * 
 */
package com.marvel.character.service.impl;

import java.io.IOException;
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

	RestClient client = null;

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

	/*
	 * (non-Javadoc)
	 * @see com.digital.pages.marvel.service.MarvelCharacterService#login(com.digital.pages.marvel.model.User)
	 */
	@Override
	public void login(User user) throws MarvelException {
		createRestClient(user);

		downloadCharacterProfile();
	}

	@Override
	@Cacheable("characters")
	public void downloadCharacterProfile() throws MarvelException {
		try {
			for(char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
				Result<MarvelCharacter> characters = client.getCharacters(new CharacterParameterBuilder().nameStartsWith(String.valueOf(alphabet)).create());
				save(characters.getData().getResults());
			}
		} catch (IOException e) {
			throw new MarvelException("Não foi possivel baixar os dados dos personagens");
		}
	}

	private void createRestClient(User user) {
		client = new RestClient(user.getPrivateKey(), user.getPublicKey());
	}

	@Override
	public MarvelCharacter findById(Integer id) {
		return marvelCharacterRepository.findOne(id);
	}

	@Override
	public Result<Comic> findComicsByCharacterId(Integer id) {
		try {
			return client.getCharactersComics(new ComicParametersBuilder(id).create());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}