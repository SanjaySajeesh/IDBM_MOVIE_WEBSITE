package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.dao.ShowDAO;
import app.model.Show;
import io.javalin.http.Handler;

public class ShowController {
    
	private static boolean entryStatusChanged = false;
	
    //display selected show 
    public static Handler serveShowPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if (ShowDAO.checkShowExists(RequestUtil.getParamShowId(ctx))) {
	        Show show = ShowDAO.getShowById(RequestUtil.getParamShowId(ctx));
	        if (EntryStatusController.checkShowEntryStatusBeforeView(ctx, show)) {
				if (entryStatusChanged) {
					show = ShowDAO.getShowById(RequestUtil.getParamShowId(ctx));
					entryStatusChanged = false;
				}
				model.put("currentUser", AccountDAO.getUserByUsername(ctx.sessionAttribute("currentUser")));
	        	model.put("show", show);
	        	model.put("creditsRoll", show.getCreditRolls());
	        	model.put("avgRating", ShowDAO.getShowAvgRating(RequestUtil.getParamShowId(ctx)));
	        	model.put("userReviews", show.getUserReviews());
	        }
	        ctx.render(Template.SHOW, model);
        }
        else {
	        ctx.render(Template.NOT_FOUND, model);
        }
    };
    
    public static void setEntryStatusChanged() {
    	entryStatusChanged = true;
    }
    
}
