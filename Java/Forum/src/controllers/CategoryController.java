package controllers;

import entities.Category;
import services.CategoryService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Created by marc on 31/03/2014.
 */
@ManagedBean
public class CategoryController {

    @EJB
    private CategoryService categoryService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private String newCategoryTitle;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public DataModel<Category> displayAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ListDataModel<Category>(categories);
    }

    public String goToBoards(Category category) {
        userBean.setActualCategory(category);
        return "boards?faces-redirect=true";
    }

    public String createNewCategory() {
        categoryService.createCategory(newCategoryTitle);
        return "categories?faces-redirect=true";
    }

    public void setNewCategoryTitle(String newCategoryTitle) {
        this.newCategoryTitle = newCategoryTitle;
    }

    public String getNewCategoryTitle() {
        return newCategoryTitle;
    }
}
