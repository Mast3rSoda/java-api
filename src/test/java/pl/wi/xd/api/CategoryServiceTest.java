package pl.wi.xd.api;

import org.junit.jupiter.api.Test;
import pl.wi.xd.api.db.entity.Category;
import pl.wi.xd.api.db.repository.CategoryRepository;
import pl.wi.xd.api.db.services.CategoryServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Test
    public void addMethodShouldCallSaveAndFindById() {
        Category newCategory = new Category("NEWNAME");
        CategoryRepository repository = mock(CategoryRepository.class);

        when(repository.save(newCategory)).thenReturn(newCategory);
        when(repository.findById(newCategory.getId())).thenReturn(Optional.of(newCategory));

        CategoryServiceImpl service = new CategoryServiceImpl(repository);
        service.add(newCategory);

        verify(repository, times(1)).save(newCategory);
        verify(repository, times(1)).findById(newCategory.getId());
    }

    @Test
    public void saveOrUpdateMethodShouldCallSaveAndFindById() {
        Category newCategory = new Category("NEWNAME");
        CategoryRepository repository = mock(CategoryRepository.class);

        when(repository.save(newCategory)).thenReturn(newCategory);
        when(repository.findById(newCategory.getId())).thenReturn(Optional.of(newCategory));

        CategoryServiceImpl service = new CategoryServiceImpl(repository);
        service.saveOrUpdate(newCategory);

        verify(repository, times(1)).findById(newCategory.getId());
        verify(repository, times(1)).save(newCategory);
    }

    @Test
    public void getByIdShouldCallGetByIdFromRepo() {
        CategoryRepository repository = mock(CategoryRepository.class);

        CategoryServiceImpl service = new CategoryServiceImpl(repository);
        service.getById(1L);

        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void getByIdShouldReturnProperObject() {
        Category category1 = new Category("NEWNAME1");
        Category category2 = new Category("NEWNAME2");
        CategoryRepository repository = mock(CategoryRepository.class);

        when(repository.findById(category1.getId())).thenReturn(Optional.of(category1));
        when(repository.findById(category2.getId())).thenReturn(Optional.of(category2));

        CategoryServiceImpl service = new CategoryServiceImpl(repository);
        Category resultCategory = service.getById(category1.getId());

        assert resultCategory.getId().equals(category1.getId());
    }

    @Test
    public void getAllShouldCallFindAllFromRepo() {
        CategoryRepository repository = mock(CategoryRepository.class);

        CategoryServiceImpl service = new CategoryServiceImpl(repository);
        service.getAll();

        verify(repository, times(1)).findAll();
    }

    @Test
    public void removeShouldCallRemoveFromRepo() {
        CategoryRepository repository = mock(CategoryRepository.class);

        CategoryServiceImpl service = new CategoryServiceImpl(repository);
        service.remove(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void getAllPaginatedShouldReturnProperObjects() {
        Category category1 = new Category("NEWNAME1");
        Category category2 = new Category("NEWNAME2");
        Category category3 = new Category("NEWNAME3");
        Category category4 = new Category("NEWNAME4");
        Category category5 = new Category("NEWNAME5");
        List<Category> categories = Arrays.asList(category1, category2, category3, category4, category5);

        CategoryRepository repository = mock(CategoryRepository.class);

        CategoryServiceImpl service = new CategoryServiceImpl(repository);
        when(repository.findAll()).thenReturn(categories);
        List<Category> resultCategories = service.getPaginated(2,2);

        verify(repository, times(1)).findAll();

        assert resultCategories.contains(category3);
        assert resultCategories.contains(category4);
    }
}
