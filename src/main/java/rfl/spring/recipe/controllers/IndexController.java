package rfl.spring.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import rfl.spring.recipe.domain.Category;
import rfl.spring.recipe.domain.UnitOfMeasure;
import rfl.spring.recipe.repositories.CategoryRepository;
import rfl.spring.recipe.repositories.UnitOfMeasureRepository;
import rfl.spring.recipe.services.RecipeService;

import java.util.Optional;

@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
