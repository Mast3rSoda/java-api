package pl.wi.xd.api.db.services;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import pl.wi.xd.api.db.entity.Category;
import pl.wi.xd.api.db.repository.CategoryRepository;
import pl.wi.xd.api.db.services.interfaces.CategoryServiceInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryServiceInterface{

    private final CategoryRepository categoryRepository;
    private Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveOrUpdate(Category category) {
        categoryRepository.save(category);

        Category changedCategory = categoryRepository.findById(category.getId()).orElse(null);
        return category;
    }

    @Override
    public Category add(Category category) {
        categoryRepository.save(category);

        Category addedCategory = categoryRepository.findById(category.getId()).orElse(null);

        if(addedCategory != null) {
            logger.info("Added new category with id={}", addedCategory.getId());
        }else {
            logger.error("Error while adding new category");
        }

        return addedCategory;
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getPaginated(int count, int offset) {
        List<Category> allCategories = getAll();
        return allCategories.stream().skip(offset).limit(count).toList();
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
        logger.info("Removed category with id={}", id);
    }
}
