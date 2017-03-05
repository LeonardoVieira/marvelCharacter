package com.marvel.character.util;

import org.springframework.util.DigestUtils;

import com.marvel.character.util.parameters.AbstractParameters;
import com.marvel.character.util.parameters.CharacterParameters;
import com.marvel.character.util.parameters.ComicParameters;

import gumi.builders.UrlBuilder;

public class URLFactory {

	private static final String BASE_URL = "http://gateway.marvel.com/v1/public/";

	private static final String CHARACTERS_BY_ID_URL = BASE_URL + "characters/%d";
	private static final String CHARACTERS_COMICS_URL = BASE_URL + "characters/%d/comics";

	private final String publicKey;
	private final String privateKey;

	public URLFactory(String privateKey, String publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}

	public String getCharactersURL(CharacterParameters characterParameters) {
		final String url = BASE_URL + "characters";
		return buildURL(url, characterParameters);
	}

	public String getCharacterURL(int characterId) {
		UrlBuilder urlBuilder = UrlBuilder.fromString(String.format(CHARACTERS_BY_ID_URL, characterId));
		return addAuthorisationParameters(urlBuilder).toString();
	}

    public String getCharactersComicsURL(ComicParameters comicParameters) {
        final String url = String.format(CHARACTERS_COMICS_URL, comicParameters.getId());
        return buildURL(url, comicParameters);
    }

	private <T extends AbstractParameters> String buildURL(String url, T parameters) {
		UrlBuilder urlBuilder = UrlBuilder.fromString(url);
		urlBuilder = parameters.addParameters(urlBuilder);
		urlBuilder = addAuthorisationParameters(urlBuilder);
		return urlBuilder.toString();
	}

	private UrlBuilder addAuthorisationParameters(UrlBuilder urlBuilder) {
		long timeStamp = System.currentTimeMillis();
		return urlBuilder.addParameter("ts", String.valueOf(timeStamp)).addParameter("apikey", publicKey)
				.addParameter("hash", createHash(timeStamp));
	}

	private String createHash(long timeStamp) {
		String stringToHash = timeStamp + privateKey + publicKey;
		return DigestUtils.md5DigestAsHex(stringToHash.getBytes());
	}
}
