/**
 * 
 */
package com.marvel.character.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author leonardo
 *
 */
public class User {

	@NotEmpty(message = "erro public key")
	private String publicKey;

	@NotEmpty(message = "erro private key")
	private String privateKey;

	/**
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * @return the privateKey
	 */
	public String getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
}