/**
 * 
 */
package com.marvel.character.web;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.marvel.character.exception.MarvelException;
import com.marvel.character.model.User;
import com.marvel.character.service.MarvelCharacterService;
import com.marvel.character.web.validator.UserValidator;

/**
 * @author Leonardo
 *
 */
@Controller
@RequestMapping()
public class MarvelCharacterController extends WebMvcConfigurerAdapter {

	private static final String MAPPING = "digital.pages.marvel.character.";
	private static final String MAPPING_LIST = MAPPING + "list";
	private static final String MAPPING_LOGIN = MAPPING + "login";
	private static final String MAPPING_PROFILE = MAPPING + "profile";

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private MarvelCharacterService marvelCharacterService;

	@Autowired
	private UserValidator userValidator;

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public @ResponseBody List<MarvelCharacter> manufactureList(Model model) {
//		return marvelCharacterService.findAll();
//	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.setValidator(userValidator);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new User());
		return MAPPING_LOGIN;
	}

	@PostMapping("login")
	public String postLogin(Model model, User user, BindingResult result) {
		try {
			userValidator.validate(user, result);

			if(result.hasErrors()) {
				StringBuilder errorMessage = new StringBuilder();

				for (ObjectError error : result.getAllErrors()) {
					errorMessage.append(error.getDefaultMessage());
					errorMessage.append("<br>");
				}

				model.addAttribute("error", errorMessage);
				return MAPPING_LOGIN;
			}

			httpSession.setAttribute("user", user);
			marvelCharacterService.downloadCharacterProfile(user.getPrivateKey(), user.getPublicKey());

			return list(model);
		} catch (MarvelException e) {
			System.out.println(e.getMessage());
			model.addAttribute("user", new User());
			model.addAttribute("error", e.getMessage());

			return MAPPING_LOGIN;
		}
	}

	@RequestMapping(value = "/marvelCharacter/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("marvelCharacterList", marvelCharacterService.findAll());
		return MAPPING_LIST;
	}

	@RequestMapping(value = "/marvelCharacter/profile", method = RequestMethod.GET)
	public String profile(Model model, @RequestParam(name = "id") Integer id) {
		try {
			model.addAttribute("marvelCharacter", marvelCharacterService.findById(id));
			User user = ((User) httpSession.getAttribute("user"));
			model.addAttribute("comics", marvelCharacterService.findComicsByCharacterId(id, user.getPrivateKey(), user.getPublicKey()).getData().getResults());

			return MAPPING_PROFILE;
		} catch (MarvelException e) {
			model.addAttribute("error", e.getMessage());

			return list(model);
		}
	}

	/**
	 * @param exception -
	 * 
	 * @return {@link ResponseEntity}<{@link Error}>
	 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception exception) {
		return "digital.pages.marvel.character.error";
	}
}