package com.digital.pages.marvel.web;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;

import com.digital.pages.marvel.exception.MarvelRestException;
import com.digital.pages.marvel.model.CollectionURI;
import com.digital.pages.marvel.model.CollectionURIDeserializer;
import com.digital.pages.marvel.model.Comic;
import com.digital.pages.marvel.model.MarvelCharacter;
import com.digital.pages.marvel.model.Result;
import com.digital.pages.marvel.util.URLFactory;
import com.digital.pages.marvel.util.parameters.CharacterParameterBuilder;
import com.digital.pages.marvel.util.parameters.CharacterParameters;
import com.digital.pages.marvel.util.parameters.ComicParameters;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

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
	public Result<MarvelCharacter> getCharacters() throws IOException {
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
	public Result<MarvelCharacter> getCharacters(CharacterParameters characterParameters) throws IOException {
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
	public Result<MarvelCharacter> getCharacter(int characterId) throws IOException {
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
	public Result<Comic> getCharactersComics(ComicParameters comicParameters) throws IOException {
		final String result = getURL(urlFactory.getCharactersComicsURL(comicParameters));
		return convertToResult(Comic.class, result);
	}

	private <T> Result<T> convertToResult(Class clazz, String result) throws IOException {
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Result.class, clazz);
		final Result<T> mappedResult = objectMapper.readValue(result, javaType);
		mappedResult.setRawResponse(result);
		return mappedResult;
	}

	private String getURL(String url) throws IOException {
		final HttpResponse httpResponse = getResponse(url);
		if (httpResponse.getStatusLine().getStatusCode() != 200) {
			throw new MarvelRestException(httpResponse);
		}
		return EntityUtils.toString(httpResponse.getEntity());
	}

	private HttpResponse getResponse(String url) throws IOException {
		if (proxy == null) {
			return Request.Get(url).execute().returnResponse();
		} else {
			return Request.Get(url).viaProxy(new HttpHost(proxy.getHost(), proxy.getPort())).execute().returnResponse();
		}
	}
}