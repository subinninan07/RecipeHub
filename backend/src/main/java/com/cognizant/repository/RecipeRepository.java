package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	List<Recipe> findAllByOrderByCreatedAtDesc();
}
