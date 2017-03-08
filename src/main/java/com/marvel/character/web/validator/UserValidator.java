/**
 * 
 */
package com.marvel.character.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.marvel.character.model.User;

/**
 * @author leonardo
 *
 */
@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "privateKey", "required.privateKey", "Private Key não foi informada");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publicKey", "required.publicKey", "Public Key não foi informada");
	}
}