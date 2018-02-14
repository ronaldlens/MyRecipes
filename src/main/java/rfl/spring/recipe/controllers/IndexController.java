package rfl.spring.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import rfl.spring.recipe.domain.Recipe;
import rfl.spring.recipe.domain.UnitOfMeasure;
import rfl.spring.recipe.services.RecipeService;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting index page");
        Set<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes", recipes);
        String logMsg = "found " + recipes.size() + " recipes";
        log.debug(logMsg);
        return "index";
    }
}
