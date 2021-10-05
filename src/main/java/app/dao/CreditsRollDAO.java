package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.CreditsRoll;
import app.model.Person;

public class CreditsRollDAO {
	public static List<CreditsRoll> getCreditRollsByShowId(int showid) {
		List<CreditsRoll> cr = new ArrayList<>();
		try {
			// Here you prepare your sql statement
			String sql = "SELECT * FROM imbd.credits_roll WHERE show_id = " + showid + ";";

			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			//get credits rolls from sql 
			while (result.next()) {
				Person p =PersonDAO.getPersonById(result.getString("person_id"));
				cr.add(new CreditsRoll(p, result.getString("role"), result.getInt("start_year"), result.getString("character_name")));
			}

			// Close it
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cr;
	}

}
