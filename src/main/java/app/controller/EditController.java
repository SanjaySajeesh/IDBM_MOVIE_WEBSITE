package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import app.dao.ProductionCompanyDAO;
import app.dao.ShowDAO;
import app.model.ProductionCompany;
import app.model.Show;
import io.javalin.http.Handler;

public class EditController {
	public static Handler serveEditShowPage = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		if (ShowDAO.checkShowExists(RequestUtil.getParamShowId(ctx))) {
			Show show = ShowDAO.getShowById(RequestUtil.getParamShowId(ctx));
			model.put("show", show);
			ctx.render(Template.EDIT_SHOW, model);
		} else {
			ctx.render(Template.NOT_FOUND, model);
		}
	};

	public static Handler postEditShowPage = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		String title = ctx.formParam("title");
		String year = ctx.formParam("year");
		double hour = Double.parseDouble(ctx.formParam("hours"));
		int min = (int) ((Integer.parseInt(ctx.formParam("mins"))/60.0)*100);
		double minHour = hour+ (min/100.0);
		String length = Double.toString(minHour);

		String isMovie = "0";
		String isSeries = "0";
		String genre = ctx.formParam("genre");

		if (ctx.formParam("showType").equals("movie")) {
			isMovie = "1";
		} else {
			isSeries = "1";
		}
		String showid = ctx.formParam("showID");
		ShowDAO.editExistingShow(showid, title, genre, length, isMovie, isSeries, year);
		ctx.redirect(Web.SHOW.replace(":showid", showid));
	};
}
