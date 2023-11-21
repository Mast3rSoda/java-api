package pl.wi.xd.api.db.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wi.xd.api.db.controller.DTO.CategoryDTO;
import pl.wi.xd.api.db.entity.Category;
import pl.wi.xd.api.db.repository.CategoryRepository;
import pl.wi.xd.api.db.services.CategoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class CategoryController {

    private final CategoryServiceImpl categoryService;
    Logger logger = LogManager.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public Category createCategory(@RequestBody String categoryName) {
        Category newCategory = new Category(categoryName);
        Category addedCategory = categoryService.add(newCategory);

        return addedCategory;
    }

    @PatchMapping("/update")
    public Category update(@RequestParam Long categoryId, String newName) {
        Category category = categoryService.getById(categoryId);
        category.setName(newName);

        return categoryService.saveOrUpdate(category);
    }

    @GetMapping("/getAll")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/getAllPaginated")
    public List<Category> getAllPaginated(@RequestParam int offset,@RequestParam int limit) {
        return categoryService.getPaginated(limit, offset);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long categoryId) {
        categoryService.remove(categoryId);
    }
}
