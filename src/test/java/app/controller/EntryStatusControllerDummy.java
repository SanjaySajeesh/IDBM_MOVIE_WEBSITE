package app.controller;

import java.util.Arrays;
import java.util.Collection;

public class EntryStatusControllerDummy {
	
	/** 
	 * Dummy versions of DAO methods called in updateAccountEntryStatus for
	 * testing purposes, to avoid executing sql statements on the live database.
	 */
	private static class AccountDAO {

		private static Collection<String> testUsernames = Arrays.asList(
				"caramel6",
				"admin"
		);
		
		/**
		 * If the record with this username does not exist, no exception will occur,
		 * but there will be no effect on the database. To 'simulate' an effect, if
		 * the username 'exists', the test will 'pass'. 
		 * @param username
		 * @param status
		 */
		public static void updateAccountEntryStatus(String username, String status) {
			if (testUsernames.contains(username)) {
				setTestPassed();
			}
		}
		
		/**
		 * If the record with this username does not exist, no exception will occur,
		 * but there will be no effect on the database. To 'simulate' an effect, if
		 * the username 'exists', the test will 'pass'. 
		 * @param username
		 */
		public static void deleteAccountEntry(String username) {
			if (testUsernames.contains(username)) {
				setTestPassed();
			}
		}
	}
	
	// Used as an indicator of updateAccountEntryStatus changing some state
	// (ie. 'updating' or 'deleting' an existing record in the database)
	private static boolean testPassed = false;
	
	public static void setTestPassed() {
		testPassed = true;
	}
	
	public static void resetTestPassed() {
		testPassed = false;
	}
	
	public static boolean getTestPassed() {
		return testPassed;
	}

	/**
	 * Method duplicated for test purposes to avoid executing sql statements on
	 * live database.
	 * @param status
	 * @param account_user
	 */
	public static void updateAccountEntryStatus(String status, String account_user) {

		switch (status) {
			case "Approve":
				AccountDAO.updateAccountEntryStatus(account_user, "APPROVED");
				break;

			case "Reject/Delete":
				AccountDAO.deleteAccountEntry(account_user);
				break;

		}
	}
}
