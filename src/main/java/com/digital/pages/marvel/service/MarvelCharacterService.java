/**
 * 
 */
package com.digital.pages.marvel.service;

import java.util.List;

import com.digital.pages.marvel.model.MarvelCharacter;
import com.digital.pages.marvel.model.User;

/**
 * @author leonardo
 *
 */
public interface MarvelCharacterService {

	public abstract List<MarvelCharacter> findAll();

	public abstract void save(List<MarvelCharacter> marvelCharacterList);
//	public abstract List<MarvelCharacter> findByName(String name);
//
//	public abstract MarvelCharacter findById(Long id);
//
//	public abstract void delete(Long id);
//
//	public abstract void save(MarvelCharacter manufacture);

	public abstract void login(User user) throws Exception;
}