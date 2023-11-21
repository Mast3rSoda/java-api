package pl.wi.xd.api.db.services.interfaces;

import pl.wi.xd.api.db.entity.Category;

import java.util.List;

public interface CategoryServiceInterface {
    Category saveOrUpdate(Category category);
    Category add(Category category);
    Category getById(Long id);
    List<Category> getAll();
    List<Category> getPaginated(int count, int offset);
    void remove(Long id);
}
