package app.controller.utils;


import app.model.Show;
import io.javalin.http.Context;

public class RequestUtil {


    public static String getQueryLoginRedirect(Context ctx) {
        return ctx.formParam("loginRedirect");
    }

    public static String getSessionCurrentUser(Context ctx) {
        return (String) ctx.sessionAttribute("currentUser");
    }

    public static String getSessionCurrentUserType(Context ctx) {
        return (String) ctx.sessionAttribute("currentUserType");
    }
    public static String getParamShowId(Context ctx) {
        return ctx.pathParam("showid");
    }
    public static String getParamPersonId(Context ctx) {
        return ctx.pathParam("person_id");

    }
    //called from IndexController
    public static String getQueryTitle(Context ctx) {
        return ctx.formParam("showTitleSearch");
    }
    //get keyword input from user
    public static String getQueryActor(Context ctx) {
        return ctx.formParam("showActorSearch");
    }
    //called from UserReviewController
    public static String getReview(Context ctx) {
        return ctx.formParam("review");
    }
	
	public static int getRating(Context ctx) {
        return Integer.parseInt(ctx.formParam("rating"));
    }
	

}
