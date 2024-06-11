package com.cognizant.service;

import java.util.List;

import com.cognizant.exception.RecipeException;
import com.cognizant.model.Recipe;
import com.cognizant.model.User;

public interface RecipeService {
	
	public Recipe createRecipe(Recipe recipe, User user);
	
	public Recipe findRecipeById(Long id) throws RecipeException;
	
	public void deleteRecipe(Long id) throws RecipeException;
	
	public Recipe updateRecipe(Recipe recipe,Long id) throws RecipeException;
	
	public List<Recipe> findAllRecipe();
	
	public Recipe likeRecipe(Long recipeId,User user) throws RecipeException;

}
