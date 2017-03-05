/**
 * 
 */
package com.marvel.character.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.marvel.character.model.User;
import com.marvel.character.service.MarvelCharacterService;

/**
 * @author Leonardo
 *
 */
@Controller
@RequestMapping()
public class MarvelCharacterController {

	private static final String MAPPING = "digital.pages.marvel.character.";
	private static final String MAPPING_LIST = MAPPING + "list";
	private static final String MAPPING_LOGIN = MAPPING + "login";
	private static final String MAPPING_PROFILE = MAPPING + "profile";

	@Autowired
	private MarvelCharacterService marvelCharacterService;

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public @ResponseBody List<MarvelCharacter> manufactureList(Model model) {
//		return marvelCharacterService.findAll();
//	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new User());
		return MAPPING_LOGIN;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLogin(Model model, User user) {
		try {
			marvelCharacterService.login(user);

			return list(model);
		} catch (Exception e) {
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
		model.addAttribute("marvelCharacter", marvelCharacterService.findAll().get(0));
		return MAPPING_PROFILE;
	}

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String include(Model model) {
//		String publicKey = "2fa4526a7bb8b4684fe2890b077e8a45";
//		String privateKey = "204599ccf56790420feae8d202fbde027858b7ee";
//
//		model.addAttribute("marvelCharacterList", marvelCharacterService.findAll());
//		return MAPPING_LIST;
//	}
}