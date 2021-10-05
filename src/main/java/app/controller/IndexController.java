package app.controller;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;




public class IndexController {
	
	
    public static Handler serveIndexPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        ctx.render(Template.INDEX, model);
    };
    
    //search by movie
    public static Handler handleSearchShowByShowTitlePost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        String showTitle = RequestUtil.getQueryTitle(ctx);
        if (showTitle != null) {
        	SearchController.setShowTitle(showTitle);
        }
        ctx.redirect(Web.SEARCH);
    };
    
    //search by actor
    public static Handler handleSearchShowByActorNamePost = ctx -> {
        String actorName = RequestUtil.getQueryActor(ctx);
        if (actorName != null) {
        	SearchController.setActorName(actorName);
        }
        ctx.redirect(Web.SEARCH);
    };
    
}
