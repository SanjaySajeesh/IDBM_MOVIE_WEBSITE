package app.controller;

import java.sql.Date;
import java.util.Map;

import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.dao.UserReviewDAO;
import io.javalin.http.Handler;

public class UserReviewController {
	
	
	public static Handler handlePostReview = ctx -> {
		Map<String, Object> model = ViewUtil.ratingReviewModel(ctx);
        String review = (String) model.get("review");
        int rating = (int) model.get("rating");
        String username = ctx.sessionAttribute("currentUser");
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        if ((username != null) && (((review != null) && (!review.isBlank())) || (rating != -1))) {
        	UserReviewDAO.addReviewsToDatabase(username,Integer.parseInt(ctx.formParam("showID")), review, rating, date);
        }
        ctx.redirect(Web.SHOW.replace(":showid", ctx.formParam("showID")));
    };
}
