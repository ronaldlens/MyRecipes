package rfl.spring.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import rfl.spring.recipe.domain.Category;
import rfl.spring.recipe.domain.UnitOfMeasure;
import rfl.spring.recipe.repositories.CategoryRepository;
import rfl.spring.recipe.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@Controller
public class IndexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage() {

        Optional<Category> categoryOptional = categoryRepository.findCategoryByName("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findUnitOfMeasureByName("Teaspoon");

        System.out.println("category: " + categoryOptional);
        System.out.println("UOM: " + unitOfMeasureOptional);
        return "index";
    }
}
