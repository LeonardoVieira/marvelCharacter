package com.marvel.character;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}

//	public static void main(String[] args) throws IOException {
//		String publicKey = "2fa4526a7bb8b4684fe2890b077e8a45";
//		String privateKey = "204599ccf56790420feae8d202fbde027858b7ee";
//
//		RestClient client = new RestClient(privateKey, publicKey);
//		Map<String, Result<MarvelCharacter>> map = new HashMap<String, Result<MarvelCharacter>>();
//
//		for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
//			String letter = String.valueOf(alphabet);
//			map.put(letter, client.getCharacters(new CharacterParameterBuilder().nameStartsWith(letter).create()));
//		}
//
//		for (Entry<String, Result<MarvelCharacter>> entry : map.entrySet()) {
//			System.out.println("*********************");
//			System.out.println("Letra: " + entry.getKey());
//			System.out.println("Total: " + entry.getValue().getData().getTotal());
//			for (MarvelCharacter marvel : entry.getValue().getData().getResults()) {
//				System.out.println(marvel.getName());
//			}
//
//			System.out.println("*********************");
//		}
//	}
}
