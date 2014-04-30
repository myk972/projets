package converters;

import entities.Topic;
import services.TopicService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by marc on 01/04/2014.
 */
@ManagedBean
public class TopicConverter implements Converter{

    @EJB
    private TopicService topicService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return topicService.getTopicFromTitle(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Topic topic = (Topic) o;
        return topic.getTitle();
    }
}
