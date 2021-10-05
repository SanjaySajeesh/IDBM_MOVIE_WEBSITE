package app.controller;

import java.util.HashMap;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.dao.ProductionCompanyDAO;
import app.model.Account;
import app.model.ProductionCompany;
import io.javalin.http.Handler;
import io.javalin.http.Context;

public class SignUpController {
	public static Handler serveSignUpPage = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		ctx.render(Template.SIGNUP, model);
	};

	// search by movie
	public static Handler handleSignUpType = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		
		Map<String, String> formParams = getFormParams(ctx);
		if (checkUsernameExists(formParams)) {
			model.put("usernameExist", true);
			model.put("signUpSubmission", false);
		} else if (!checkMatchingPassword(formParams)) {
			model.put("passwordMismatch", true);
			model.put("signUpSubmission", false);
		} else if (formParams.get("type").equals(Account.PCO) && !checkProductionCompany(formParams)) {
			model.put("noPCo", true);
			model.put("signUpSubmission", false);
		} else if (checkSubmissionToDatabase(formParams)){
			model.put("signUpSubmission", true);
		}
		ctx.render(Template.SIGNUP, model);
	};
	
	private static Map<String, String> getFormParams(Context ctx) {
		
		Map<String, String> formParams = new HashMap<String, String>();
		String[] params = {
				"username",
				"password",
				"confirm_password",
				"firstname",
				"lastname",
				"gender",
				"year",
				"country",
				"postalcode",
				"email",
				"organisation",
				"phoneNum",
				"type",
		};
		for (String param : params) {
			formParams.put(param, ctx.formParam(param));
		}
		return formParams;
	}
	
	public static boolean checkUsernameExists(Map<String, String> formParams) {
		return (AccountDAO.getUserByUsername(formParams.get("username")) != null);
	}
	
	public static boolean checkMatchingPassword(Map<String, String> formParams) {
		return (formParams.get("password").equals(formParams.get("confirm_password")));		
	}
	
	public static boolean checkSubmissionToDatabase(Map<String, String> formParams) {
		
		String un = formParams.get("username");
		String hashed_pass = UserController.getHashedPassword(formParams.get("password"));

		String fn = formParams.get("firstname");
		String ln = formParams.get("lastname");
		String gender = formParams.get("gender");
		String year = formParams.get("year");
		String country = formParams.get("country");
		String postCode = formParams.get("postalcode");
		String email = formParams.get("email");
		String organisation = formParams.get("organisation");
		String phoneNum = formParams.get("phoneNum");
		String account_type = formParams.get("type");
		
		//add entry to database
		return AccountDAO.addNewAccount(un, hashed_pass, fn, ln, gender, year, country, postCode, email, organisation, phoneNum, "PENDING", account_type);
		
	}
	public static boolean checkProductionCompany(Map<String, String> formParams) {
		//check if inputed PCO name matches with PCO in the database
		//NOTE: PCO user can be created only if PCO exist in database
		for (ProductionCompany pco : ProductionCompanyDAO.getAllProCos()) {
			if(pco.getName().toLowerCase().equals(formParams.get("organisation").toLowerCase())) {
				formParams.put("organisation", pco.getName());
				return true;
			}
		}
		return false;
	}
}
