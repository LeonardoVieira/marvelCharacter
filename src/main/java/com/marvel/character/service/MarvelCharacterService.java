/**
 * 
 */
package com.marvel.character.service;

import java.util.List;

import com.marvel.character.exception.MarvelException;
import com.marvel.character.model.Comic;
import com.marvel.character.model.MarvelCharacter;
import com.marvel.character.model.Result;

/**
 * @author leonardo
 *
 */
public interface MarvelCharacterService {

	public abstract List<MarvelCharacter> findAll();

	public abstract void save(List<MarvelCharacter> marvelCharacterList);

	public abstract MarvelCharacter findById(Integer id);

	public abstract Result<Comic> findComicsByCharacterId(Integer id, String privateKey, String publicKey) throws MarvelException;

	public abstract void downloadCharacterProfile(String privateKey, String publicKey) throws MarvelException;
}