package dao;

import entities.Category;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by marc on 31/03/2014.
 */
@Local
public interface CategoryDao {

    public List<Category> getAllCategories();

    public Category getCategoryFromName(String name);

    public void createCategory(Category category);
}
