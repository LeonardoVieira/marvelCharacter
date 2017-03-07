package com.marvel.character.service.impl;

import java.io.IOException;
import java.util.List;

import com.marvel.character.model.MarvelCharacter;
import com.marvel.character.model.Result;
import com.marvel.character.util.parameters.CharacterParameterBuilder;
import com.marvel.character.web.RestClient;

public class ThreadDownload extends Thread {

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
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public void downloadCharacterProfile(RestClient client, List<MarvelCharacter> list, Integer offset) throws IOException {
		Result<MarvelCharacter> characters = client.getCharacters(new CharacterParameterBuilder().withOffset(offset).withLimit(100).create());
		list.addAll(characters.getData().getResults());
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