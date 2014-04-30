package converters;


import entities.Category;
import services.CategoryService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by marc on 01/04/2014.
 */
@ManagedBean
public class CategoryConverter implements Converter {

    @EJB
    private CategoryService categoryService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return categoryService.getCategoryFromName(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Category category = (Category) o;
        return category.getName();
    }
}
