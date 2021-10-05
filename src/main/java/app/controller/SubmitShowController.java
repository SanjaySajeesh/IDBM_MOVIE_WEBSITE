package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ProductionCompanyDAO;
import app.dao.ShowDAO;
import app.model.ProductionCompany;
import app.model.Account;
import app.model.Show;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class SubmitShowController {
	public static Handler serveSubmitShowPage = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		List<ProductionCompany> proCos = ProductionCompanyDAO.getAllProCos();
		model.put("proCos", proCos);
		ctx.render(Template.SUBMIT_SHOW, model);
	};

	public static Handler handleSubmitShowPost = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		
		if (checkSubmissionToDatabase(ctx)) {		
			model.put("submission", true);
		} else {
			model.put("submission", false);
		}
		
		model.put("proCos", ProductionCompanyDAO.getAllProCos());
		ctx.render(Template.SUBMIT_SHOW, model);
	};

	// check the integrity of data of new entry submission
	private static boolean checkSubmissionToDatabase(Context ctx) {
		Map<String, Object> model = ViewUtil.baseModel(ctx);

		String showTitle = ctx.formParam("title");
		String year = ctx.formParam("year");
		
		double hour = Double.parseDouble(ctx.formParam("hours"));
		int min = (int) ((Integer.parseInt(ctx.formParam("mins"))/60.0)*100);
		double minHour = hour+ (min/100.0);
		String length = Double.toString(minHour);

		String isMovie = "0";
		String isSeries = "0";
		String genre = ctx.formParam("genre");
		String proCo = ctx.formParam("proCo");
		
		if (ctx.formParam("showType")==null) {
			return false;
		}else if (ctx.formParam("showType").equals("movie")) {
			isMovie = "1";
		} else {
			isSeries = "1";
		}

		// check if PCo is author and set status accordingly
		if (Account.PCO.equals(ctx.sessionAttribute("currentUserType"))) {
			//add to database
			return ShowDAO.addNewShowEntry(showTitle, genre, length, isMovie, isSeries, proCo, year,
					(String) model.get("currentUser"), Show.SUBMITTED);
		} else {
			//add to database
			return ShowDAO.addNewShowEntry(showTitle, genre, length, isMovie, isSeries, proCo, year,
					(String) model.get("currentUser"), Show.UNDER_INVESTIGATION);
		}

	}
}
