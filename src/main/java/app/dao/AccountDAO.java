package app.dao;

import app.dao.utils.DatabaseUtils;
import app.model.Account;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO: simplify this page: getUserByUsername could be used for printing to
// 		 Account page, and a simpler method could be used for login authentication

public class AccountDAO {
	public static final String SALT = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";

	/**
	 * Method to fetch users from the database. You should use this as an example
	 * for future queries, though the sql statement will change -and you are
	 * supposed to write them.
	 *
	 * Current user: caramel 6, password (the password is "password" without quotes)
	 * 
	 * @param username what the user typed in the log in form.
	 * @return Some of the user data to check on the password. Null if there no
	 *         matching user.
	 */
	private static List<Account> getAccountGenericMethod(String sql){
		List<Account> accounts = new ArrayList<>();
		try {
			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			
			if(statement.execute(sql)) {
				ResultSet result = statement.getResultSet();
		
				// If you have multiple results, you do a while
				while (result.next()) {
					// 2) Add it to the list we have prepared
					//create Account for public users and admin
					if(result.getString("type").equals( Account.ADMIN) || result.getString("type").equals(Account.USER)) {
						accounts.add(
								// 1) Create a new account object
								new Account(result.getString("type"), result.getString("username"),
										result.getString("password"), result.getString("first_name"),
										result.getString("last_name"), null, result.getString("country"),
										result.getString("gender"), result.getString("email")));
					}
					//create Account for PCO or Critic users
					else {
						accounts.add(
								new Account(result.getString("type"), result.getString("username"),
										result.getString("password"), result.getString("first_name"),
										result.getString("last_name"), null, result.getString("country"),
										result.getString("gender"), result.getString("email"),
										
										result.getString("phone_num"), result.getString("organisation"), result.getString("postal_code")
										)
								);
					}
				}
			}

			// Close it
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// If there is a result
		if (!accounts.isEmpty())
			return accounts;
		// If we are here, something bad happened
		return null;
	}
	public static Account getUserByUsername(String username) {
		List<Account> account = getAccountGenericMethod("SELECT * FROM account WHERE username ='" + username + "' AND account_status = 'APPROVED'");
		if (account != null) {
			return account.get(0);
		}
		return null;
	}
	public static List<Account> getPendingAccount(){
		return getAccountGenericMethod("SELECT * FROM imbd.account WHERE account_status = 'PENDING';");
	}
	public static void updateAccountEntryStatus(String username, String status) {
		getAccountGenericMethod("UPDATE imbd.account SET account_status = '" + status + "' WHERE username = '" + username + "';");
	}
	public static void deleteAccountEntry(String username) {
		getAccountGenericMethod("DELETE FROM imbd.account WHERE username = '" + username + "';");
	}
	

	public static boolean addNewAccount(String un, String hashed_pass, String fn, String ln, String gender, String year,
			String country, String postCode, String email, String organisation, String phoneNum, String status,
			String type) {
		try {
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();

			organisation = organisation.replace("'", "\\'");

			String sql = "INSERT INTO imbd.account (username, password, type, email, country, gender, first_name, last_name, postal_code, phone_num, organisation, account_status)"
					+ "VALUES ('" + un + "', '" + hashed_pass + "', '" + type + "', '" + email + "', '" + country
					+ "', '" + gender + "', '" + fn + "', '" + ln + "', '" + postCode + "', '" + phoneNum + "', '"
					+ organisation + "', '" + status + "');";
			// Execute the query
			statement.execute(sql);
			// Close it
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
