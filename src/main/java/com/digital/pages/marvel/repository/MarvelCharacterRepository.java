package com.digital.pages.marvel.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.digital.pages.marvel.model.MarvelCharacter;

public interface MarvelCharacterRepository extends JpaRepository<MarvelCharacter, Serializable>, QueryByExampleExecutor<MarvelCharacter> {

	List<MarvelCharacter> findAll(Sort sort);
}