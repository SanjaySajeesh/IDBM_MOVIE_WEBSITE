package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import app.model.Show;
import io.javalin.http.Handler;

public class AdminShowEntryListController {
	
	private static String filterByStatus = null;
	
	public static Handler serveAdminShowEntryListNoFilter = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		filterByStatus = null;
		List<Show> entries = ShowDAO.getShowByStatus(filterByStatus);
		if (EntryStatusController.checkSubmittedPendingBeforeView(entries)) {
			entries = ShowDAO.getShowByStatus(filterByStatus);
		};
		model.put("entries", entries);
		ctx.render(Template.ADMIN_SHOW_ENTRY_LIST, model);
	};
	
	public static Handler serveAdminShowEntryListFiltered = ctx -> {
		String newFilter = ctx.formParam("filterByStatus");
		if ("No filter".equals(newFilter)) {
			ctx.redirect(Web.ADMIN_SHOW_ENTRY_LIST);
			return;
		}
		else {
			filterByStatus = newFilter;
		}
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		model.put("entries", ShowDAO.getShowByStatus(filterByStatus));
		model.put("filterByStatus", filterByStatus);
		ctx.render(Template.ADMIN_SHOW_ENTRY_LIST, model);
	};
	
	public static Handler handleAdminShowEntryUpdateStatusPost = ctx -> {
		EntryStatusController.updateEntryStatus(ctx);
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		model.put("entries", ShowDAO.getShowByStatus(filterByStatus));
		model.put("filterByStatus", filterByStatus);
		ctx.render(Template.ADMIN_SHOW_ENTRY_LIST, model);
	};
	
	public static Handler handleAdminShowEntryUpdateStatusFromPagePost = ctx -> {
		EntryStatusController.updateEntryStatus(ctx);		
		ctx.redirect(Web.SHOW.replace(":showid", ctx.formParam("showid")));
	};
	
}
