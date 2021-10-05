package app.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import app.model.Account;
import app.model.ProductionCompany;

public class SignUpControllerDummy {
	
	/** 
	 * Dummy versions of DAO methods called in SignUpController for
	 * testing purposes, to avoid executing sql statements on the live database.
	 */
	private static class AccountDAO {
		
		private static Collection<String> testUsernames = Arrays.asList(
				"caramel6",
				"admin"
		);
		
		/**
		 * Dummy version of the DAO method called in checkUsernameExists.
		 * This method imitates the real method which returns an Account if a
		 * record with the username exists in the database. As checkUsernameExists
		 * only checks if the returned Account is not null, an arbitrary Account
		 * is returned when the username 'exists' in the Collection above.
		 */
		public static Account getUserByUsername(String username) {
			
			if (testUsernames.contains(username)) {
				return new Account(null, null, null, null, null, null, null, null, null);
			} else {
				return null;
			}
		}
	
		/**
		 * Dummy version of the DAO method called in checkSubmissionToDatabase.
		 * The failing cases are:
		 * 		-if postCode or phoneNum is not a number.
		 * 		-if any of username, password, email, country, gender, firstname or
		 * 		 lastname are null.
		 * 
		 * In any of these cases, an SQLException will be thrown and caught, and the
		 * method will return false. This method imitates the real method by
		 * checking for invalid values.
		 */
		public static boolean addNewAccount(String un, String hashed_pass, String fn, String ln, String gender, String year,
				String country, String postCode, String email, String organisation, String phoneNum, String status,
				String type) {
			
			if ((un == null) || (hashed_pass == null) || (fn == null) || (ln == null)
					|| (gender == null) || (country == null) || (email == null)
					|| !postCode.matches("\\d+") || !phoneNum.matches("\\d+")) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	/**
	 * Dummy version of the DAO method called in checkProductionCompany for
	 * testing purposes, to avoid executing sql statements on the live database.
	 */
	private static class ProductionCompanyDAO {
		
		public static List<ProductionCompany> getAllProCos() {
			return Arrays.asList(
					new ProductionCompany("Universal Pictures", "1"),
					new ProductionCompany("Paramount Pictures", "2"),
					new ProductionCompany("20th Century Fox", "3"),
					new ProductionCompany("Warner Bros.", "4"),
					new ProductionCompany("DreamWorks Pictures", "5"),
					new ProductionCompany("Metro-Goldwyn-Meyer", "6"),
					new ProductionCompany("Miramax", "7"),
					new ProductionCompany("Columbia Pictures", "8"),
					new ProductionCompany("Walt Disney Pictures", "9"),
					new ProductionCompany("Sony Pictures", "10"),
					new ProductionCompany("New Line Cinema", "11"),
					new ProductionCompany("Cinelou Films", "12")
					);
		}
	}
	
	/**
	 * Method duplicated for test purposes to avoid executing sql statements on
	 * live database.
	 */
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
	
	/**
	 * Method duplicated for test purposes to avoid executing sql statements on
	 * live database.
	 */
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
	
	/**
	 * Method duplicated for test purposes to avoid executing sql statements on
	 * live database.
	 */
	public static boolean checkUsernameExists(Map<String, String> formParams) {
		return (AccountDAO.getUserByUsername(formParams.get("username")) != null);
	}
}
