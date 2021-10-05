package app.controller.utils;


import java.util.HashMap;
import java.util.Map;

import app.controller.paths.Template;
import io.javalin.http.Context;
import io.javalin.http.ErrorHandler;





public class ViewUtil {


    public static Map<String, Object> baseModel(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        model.put("currentUser", RequestUtil.getSessionCurrentUser(ctx));
        model.put("currentUserType", RequestUtil.getSessionCurrentUserType(ctx));
        return model;
    }

    public static ErrorHandler notFound = ctx -> {
        ctx.render(Template.NOT_FOUND, baseModel(ctx));
    };
    
    public static Map<String, Object> ratingReviewModel(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        model.put("currentUser", RequestUtil.getSessionCurrentUser(ctx));
       // model.put("show", ShowDAO.getShowById(RequestUtil.getParamShowId(ctx)));
        model.put("rating", RequestUtil.getRating(ctx));
        model.put("review", RequestUtil.getReview(ctx));
        return model;
    }
    

}
