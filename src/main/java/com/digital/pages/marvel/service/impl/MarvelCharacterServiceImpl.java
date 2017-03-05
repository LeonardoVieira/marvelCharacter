/**
 * 
 */
package com.digital.pages.marvel.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.pages.marvel.model.MarvelCharacter;
import com.digital.pages.marvel.model.Result;
import com.digital.pages.marvel.model.User;
import com.digital.pages.marvel.repository.MarvelCharacterRepository;
import com.digital.pages.marvel.service.MarvelCharacterService;
import com.digital.pages.marvel.util.parameters.CharacterParameterBuilder;
import com.digital.pages.marvel.web.RestClient;

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

	/*
	 * (non-Javadoc)
	 * @see com.digital.pages.marvel.service.MarvelCharacterService#login(com.digital.pages.marvel.model.User)
	 */
	@Override
	public void login(User user) throws Exception {
		try {
			RestClient client = new RestClient(user.getPrivateKey(), user.getPublicKey());

			for(char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
				Result<MarvelCharacter> characters = client.getCharacters(new CharacterParameterBuilder().nameStartsWith(String.valueOf(alphabet)).create());
				save(characters.getData().getResults());
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
			// TODO: handle exception
			//TODO THROW LOGIN EXCEPTION
		}
	}
}