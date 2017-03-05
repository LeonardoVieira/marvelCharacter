package com.marvel.character.util.parameters;

import gumi.builders.UrlBuilder;

public class CharacterParameters extends AbstractParameters {
	private String name;
	private String startsWith;

	@Override
	public UrlBuilder addParameters(UrlBuilder urlBuilder) {
		urlBuilder = super.addParameters(urlBuilder);
		urlBuilder = addParameterToUrl("name", name, urlBuilder);
		urlBuilder = addParameterToUrl("nameStartsWith", startsWith, urlBuilder);
		return urlBuilder;
	}

	void setName(String name) {
		this.name = name;
	}

	public void setNameStartsWith(String startsWith) {
		this.startsWith = startsWith;
	}
}
