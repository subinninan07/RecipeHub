package com.cognizant.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.exception.RecipeException;
import com.cognizant.model.Recipe;
import com.cognizant.model.User;
import com.cognizant.repository.RecipeRepository;

@Service
public class RecipeServiceImplementation implements RecipeService{
	
	@Autowired
	public RecipeRepository recipeRepository;
	
	@Autowired
	public UserService userService;

	@Override
	public Recipe createRecipe(Recipe recipe, User user) {
		
		Recipe newRecipe=new Recipe();
		newRecipe.setDescription(recipe.getDescription());
		newRecipe.setImage(recipe.getImage());
		newRecipe.setTitle(recipe.getTitle());
		newRecipe.setVegetarian(recipe.isVegetarian());
		newRecipe.setUser(user);
		newRecipe.setCreatedAt(LocalDateTime.now());
		newRecipe.setIngredients(recipe.getIngredients());
		newRecipe.setVegornonveg(recipe.getVegornonveg());
		
		return recipeRepository.save(newRecipe);
	}

	@Override
	public Recipe findRecipeById(Long id) throws RecipeException {
		Optional<Recipe> opt=recipeRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
	throw new RecipeException("Recipe not found with id "+id);
	}

	@Override
	public void deleteRecipe(Long id) throws RecipeException {
		Recipe recipe=findRecipeById(id);
		
		recipeRepository.delete(recipe);
		
	}

	@Override
	public Recipe updateRecipe(Recipe recipe,Long id) throws RecipeException {
		Recipe oldRecipe = findRecipeById(id);
		
		if(recipe.getDescription()!=null) {
			oldRecipe.setDescription(recipe.getDescription());
		}
		if(recipe.getImage()!=null) {
			oldRecipe.setImage(recipe.getImage());
		}
		if(recipe.getTitle()!=null) {
			oldRecipe.setTitle(recipe.getTitle());
		}
		
		if (recipe.getIngredients() != null) {
			oldRecipe.setIngredients(recipe.getIngredients());
		}

		if (!recipe.isVegetarian()) {
			oldRecipe.setVegetarian(recipe.isVegetarian());
		}

		if (recipe.getVegornonveg() != null) {
			oldRecipe.setVegornonveg(recipe.getVegornonveg());
		}

		return recipeRepository.save(oldRecipe);
	}

	@Override
	public List<Recipe> findAllRecipe() {
		// TODO Auto-generated method stub
		return recipeRepository.findAllByOrderByCreatedAtDesc();
	}

	@Override
	public Recipe likeRecipe(Long recipeId, User user) throws RecipeException {
		Recipe recipe=findRecipeById(recipeId);
		if(recipe.getLikes()==null) {
			recipe.setLikes(new ArrayList<>());
		}
		if(recipe.getLikes().contains(user.getId())) {
			recipe.getLikes().remove(user.getId());
		}
		else {
			recipe.getLikes().add(user.getId());
		}
		
		return recipeRepository.save(recipe);
	}

}
