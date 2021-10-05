package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import app.dao.PersonDAO;
import app.model.Person;
import io.javalin.http.Handler;

public class PersonController {

	//display selected person from show page 
    public static Handler servePersonPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        Person person = PersonDAO.getPersonById(RequestUtil.getParamPersonId(ctx));
        model.put("person", person);
        ctx.render(Template.PERSON, model);
    };
}
