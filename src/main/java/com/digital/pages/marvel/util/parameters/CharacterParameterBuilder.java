package com.digital.pages.marvel.util.parameters;

public class CharacterParameterBuilder {

	private final CharacterParameters characterParameters;

	public CharacterParameterBuilder() {
		this.characterParameters = new CharacterParameters();
	}

	public CharacterParameterBuilder withName(String name) {
		characterParameters.setName(name);
		return this;
	}

	public CharacterParameterBuilder withLimit(Integer limit) {
		characterParameters.setLimit(limit);
		return this;
	}

	public CharacterParameterBuilder nameStartsWith(String startsWith) {
		characterParameters.setNameStartsWith(startsWith);
		return this;
	}

	public CharacterParameters create() {
		return characterParameters;
	}
}
