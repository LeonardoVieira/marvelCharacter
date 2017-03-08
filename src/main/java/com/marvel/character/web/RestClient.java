package com.marvel.character.web;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.marvel.character.exception.MarvelException;
import com.marvel.character.model.CollectionURI;
import com.marvel.character.model.CollectionURIDeserializer;
import com.marvel.character.model.Comic;
import com.marvel.character.model.MarvelCharacter;
import com.marvel.character.model.Result;
import com.marvel.character.util.Proxy;
import com.marvel.character.util.URLFactory;
import com.marvel.character.util.parameters.CharacterParameterBuilder;
import com.marvel.character.util.parameters.CharacterParameters;
import com.marvel.character.util.parameters.ComicParameters;

public class RestClient {

	private final URLFactory urlFactory;
	private final ObjectMapper objectMapper;
	private Proxy proxy;

	public RestClient(String privateKey, String publicKey) {
		this.urlFactory = new URLFactory(privateKey, publicKey);
		this.objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("CollectionURIDeserializerModule",
				new Version(1, 0, 0, null, null, null));
		module.addDeserializer(CollectionURI.class, new CollectionURIDeserializer());
		objectMapper.registerModule(module);
	}

	public RestClient(String privateKey, String publicKey, Proxy proxy) {
		this(privateKey, publicKey);
		this.proxy = proxy;
	}

	/**
	 * Fetches lists of characters.
	 *
	 * @param characterParameters
	 * @return
	 * @throws IOException
	 */
	public Result<MarvelCharacter> getCharacters() throws MarvelException {
		final String result = getURL(urlFactory.getCharactersURL(new CharacterParameterBuilder().create()));
		return convertToResult(MarvelCharacter.class, result);
	}

	/**
	 * Fetches lists of characters.
	 *
	 * @param characterParameters
	 * @return
	 * @throws IOException
	 */
	public Result<MarvelCharacter> getCharacters(CharacterParameters characterParameters) throws MarvelException {
		final String result = getURL(urlFactory.getCharactersURL(characterParameters));
		return convertToResult(MarvelCharacter.class, result);
	}

	/**
	 * Fetches lists of characters filtered by character id.
	 *
	 * @param characterId
	 * @return
	 * @throws IOException
	 */
	public Result<MarvelCharacter> getCharacter(int characterId) throws MarvelException {
		final String result = getURL(urlFactory.getCharacterURL(characterId));
		return convertToResult(MarvelCharacter.class, result);
	}
	
	/**
	 * Fetches lists of comics filtered by character id.
	 *
	 * @param comicParameters
	 * @return
	 * @throws IOException
	 */
	public Result<Comic> getCharactersComics(ComicParameters comicParameters) throws MarvelException {
		final String result = getURL(urlFactory.getCharactersComicsURL(comicParameters));
		return convertToResult(Comic.class, result);
	}

	private <T> Result<T> convertToResult(Class clazz, String result) throws MarvelException {
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Result.class, clazz);
			final Result<T> mappedResult = objectMapper.readValue(result, javaType);
			mappedResult.setRawResponse(result);

			return mappedResult;
		} catch (IOException e) {
			throw new MarvelException("Erro ao converter resultado");
		}
	}

	private String getURL(String url) throws MarvelException {
		try {
			final HttpResponse httpResponse = getResponse(url);

			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				throw new MarvelException(httpResponse.getStatusLine().getReasonPhrase());
			}

			return EntityUtils.toString(httpResponse.getEntity());
		} catch (IOException e) {
			throw new MarvelException("Erro ao recuperar URL");
		}
	}

	private HttpResponse getResponse(String url) throws IOException {
		if (proxy == null) {
			return Request.Get(url).execute().returnResponse();
		} else {
			return Request.Get(url).viaProxy(new HttpHost(proxy.getHost(), proxy.getPort())).execute().returnResponse();
		}
	}
}
