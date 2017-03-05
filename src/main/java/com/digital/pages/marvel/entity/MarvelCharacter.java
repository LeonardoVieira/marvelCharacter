///**
// * 
// */
//package com.digital.pages.marvel.entity;
//
//import java.time.LocalDate;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//
///**
// * @author leonardo
// *
// */
//@Entity(name = "CHARACTER")
//public class MarvelCharacter {
//
//	@Column(name = "ID", columnDefinition = "The unique ID of the character resource")
//	private Long id;
//
//	@Column(name = "NAME", columnDefinition = "The name of the character")
//	private String name;
//
//	@Column(name = "DESCRIPTION", columnDefinition = "A short bio or description of the character")
//	private String description;
//
//	@Column(name = "MODIFIED", columnDefinition = "The date the resource was most recently modified")
//	private LocalDate modified;
//
//	@Column(name = "THUMBNAIL", columnDefinition = "The representative image for this character")
//	private Image thumbnail;
//
//	@ManyToMany
//	@JoinTable(name = "CHARACTER_COMIC",
//			joinColumns = {
//					@JoinColumn(name = "CHARACTER_ID") },
//			inverseJoinColumns = {
//					@JoinColumn(name = "COMIC_ID") })
//	private Comic[] comics;
//
//	/**
//	 * @return the id
//	 */
//	public Long getId() {
//		return id;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	/**
//	 * @return the name
//	 */
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * @param name the name to set
//	 */
//	public void setName(String name) {
//		this.name = name;
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
//	 * @return the modified
//	 */
//	public LocalDate getModified() {
//		return modified;
//	}
//
//	/**
//	 * @param modified the modified to set
//	 */
//	public void setModified(LocalDate modified) {
//		this.modified = modified;
//	}
//
//	/**
//	 * @return the thumbnail
//	 */
//	public Image getThumbnail() {
//		return thumbnail;
//	}
//
//	/**
//	 * @param thumbnail the thumbnail to set
//	 */
//	public void setThumbnail(Image thumbnail) {
//		this.thumbnail = thumbnail;
//	}
//
//	/**
//	 * @return the comics
//	 */
//	public Comic[] getComics() {
//		return comics;
//	}
//
//	/**
//	 * @param comics the comics to set
//	 */
//	public void setComics(Comic[] comics) {
//		this.comics = comics;
//	}
//}