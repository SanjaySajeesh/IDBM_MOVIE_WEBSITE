package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.Person;
import app.model.ProductionCompany;

public class PersonDAO {
	private static List<Person> getShowGenericMethod(String sql) {
		List<Person> person = new ArrayList<>();
		try {
			// Here you prepare your sql statement

			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			//store result in Person class
			while (result.next()) {
				person.add(new Person(result.getInt("person_id"), result.getString("fullname"),
						result.getString("role"), result.getDate("birthdate"), result.getString("bio")));			}

			// Close it
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO I think this can just be 'return proco' as it should still be
		// null if there is no result
		if(person != null) return person;
		return null;
	}
	public static Person getPersonById(String personId) {
		return getShowGenericMethod("SELECT * FROM imbd.person WHERE person_id = " + personId + ";").get(0);
	}
	
	public static List<Person> getPersonByName(String actor){
		return getShowGenericMethod("select * from person WHERE fullname LIKE '%" + actor + "' OR fullname LIKE '" + actor
				+ "%' OR fullname LIKE '%" + actor + "%'");
	}
	
	
}
