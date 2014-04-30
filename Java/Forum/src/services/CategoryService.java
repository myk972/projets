package services;

import dao.CategoryDao;
import entities.Category;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marc on 31/03/2014.
 */
@Stateless
public class CategoryService {

    @EJB
    private CategoryDao categoryDao;

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public Category getCategoryFromName(String name) {
        return categoryDao.getCategoryFromName(name);
    }

    public void createCategory(String title) {
        Category category = new Category();
        category.setName(title);
        categoryDao.createCategory(category);
    }
}
