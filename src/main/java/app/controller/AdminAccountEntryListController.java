package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.dao.ShowDAO;
import app.model.Account;
import io.javalin.http.Handler;

public class AdminAccountEntryListController {
	public static Handler serveAdminAccountEntryList = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		List<Account> pendingAccount = AccountDAO.getPendingAccount();
		model.put("pendingAccount", pendingAccount);
		ctx.render(Template.ADMIN_ACCOUNT_ENTRY_LIST, model);
	};
	
	public static Handler handleAdminAccountEntryUpdateStatusPost = ctx -> {
		String status = ctx.formParam("statusUpdate");
		String account_user = ctx.formParam("account_user");
		EntryStatusController.updateAccountEntryStatus(status, account_user);
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		
		model.put("pendingAccount", AccountDAO.getPendingAccount());
		ctx.render(Template.ADMIN_ACCOUNT_ENTRY_LIST, model);
	};
	
}
