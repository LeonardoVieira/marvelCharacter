///**
// * 
// */
//package com.digital.pages.marvel.entity;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.ManyToMany;
//
///**
// * @author leonardo
// *
// */
//public class Comic {
//
//	@Column(name = "ID", columnDefinition = "The unique ID of the comic resource")
//	private Integer id;
//
//	@Column(name = "DIGITAL_ID", columnDefinition = "The ID of the digital comic representation of this comic. Will be 0 if the comic is not available digitally")
//	private Integer digitalId;
//
//	@Column(name = "TITLE", columnDefinition = "The canonical title of the comic")
//	private String title;
//
//	@Column(name = "DESCRIPTION", columnDefinition = "The preferred description of the comic")
//	private String description;
//
//	@ManyToMany(mappedBy="COMICS")
//	private List<MarvelCharacter> characters;
//
//	/**
//	 * @return the id
//	 */
//	public Integer getId() {
//		return id;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	/**
//	 * @return the title
//	 */
//	public String getTitle() {
//		return title;
//	}
//
//	/**
//	 * @param title the title to set
//	 */
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	/**
//	 * @return the description
//	 */
//	public String getDescription() {
//		return description;
//	}
//
//	/**
//	 * @param description the description to set
//	 */
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	/**
//	 * @return the digitalId
//	 */
//	public Integer getDigitalId() {
//		return digitalId;
//	}
//
//	/**
//	 * @param digitalId the digitalId to set
//	 */
//	public void setDigitalId(Integer digitalId) {
//		this.digitalId = digitalId;
//	}
//
//	public List<MarvelCharacter> getCharacters() {
//		return characters;
//	}
//
//	public void setCharacters(List<MarvelCharacter> characters) {
//		this.characters = characters;
//	}
//}