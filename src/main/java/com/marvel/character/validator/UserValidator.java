package com.marvel.character.validator;
//package com.digital.pages.validator;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//import com.digital.pages.model.Employee;
//import com.digital.pages.service.UserService;
//
//@Component
//public class UserValidator implements Validator {
//
//	@Autowired
//	private UserService userService;
//
//	@Override
//	public boolean supports(Class<?> aClass) {
//		return Employee.class.equals(aClass);
//	}
//
//	@Override
//	public void validate(Object o, Errors errors) {
//		Employee user = (Employee) o;
//
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
////		if (user.getLogin().length() < 6 || user.getLogin().length() > 32) {
////			errors.rejectValue("username", "Size.userForm.username");
////		}
//
//		if (userService.findByUsername(user.getLogin()) != null) {
//			errors.rejectValue("username", "Duplicate.userForm.username");
//		}
//
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//
//		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
//			errors.rejectValue("password", "Size.userForm.password");
//		}
//
////		if (!user.getPasswordConfirm().equals(user.getPassword())) {
////			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
////		}
//	}
//}
