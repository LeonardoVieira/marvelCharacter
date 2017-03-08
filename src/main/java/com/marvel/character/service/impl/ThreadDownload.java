package com.marvel.character.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.marvel.character.exception.MarvelException;
import com.marvel.character.model.MarvelCharacter;
import com.marvel.character.model.Result;
import com.marvel.character.util.parameters.CharacterParameterBuilder;
import com.marvel.character.web.RestClient;

public class ThreadDownload extends Thread {

	private static final Logger LOGGER = Logger.getLogger(ThreadDownload.class.getName());

	private int offset;

	private int size;

	private List<MarvelCharacter> list;

	private RestClient client;

	public ThreadDownload(RestClient client, int offset, List<MarvelCharacter> list) {
		super();
		this.offset = offset;
		this.list = list;
		this.client = client;
		this.size = offset + 400;
	}

	@Override
	public void run() {
		try {
			for(; offset <= size; offset = offset + 100) {
				Result<MarvelCharacter> characters = client.getCharacters(new CharacterParameterBuilder().withOffset(offset).withLimit(100).create());
				list.addAll(characters.getData().getResults());
			}
		} catch (MarvelException e) {
			LOGGER.log(Level.SEVERE, "Erro ao fazer download dos dados dos personagens");
		}
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public List<MarvelCharacter> getList() {
		return list;
	}

	public void setList(List<MarvelCharacter> list) {
		this.list = list;
	}

	public RestClient getClient() {
		return client;
	}

	public void setClient(RestClient client) {
		this.client = client;
	}
}