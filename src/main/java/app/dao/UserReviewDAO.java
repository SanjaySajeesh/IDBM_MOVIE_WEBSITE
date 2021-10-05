package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.UserReview;

public class UserReviewDAO {
	public static List<UserReview> getUserReviewsByShowId(int showid) {
		List<UserReview> ur = new ArrayList<>();
		try {
			// Here you prepare your sql statement
			String sql = "SELECT * FROM imbd.user_review WHERE show_id = " + showid + ";";

			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			//get credits rolls from sql 
			while (result.next()) {
				
				ur.add(new UserReview(result.getString("user_id"), showid, result.getString("review"), (result.getString("rating") != null) ? result.getInt("rating") : null, result.getDate("date")));
			}

			// Close it
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
			return ur;
	
	}
	
	public static void addReviewsToDatabase(String username, int showid, String review, int rating, Date date) {
		try {
			// Here you prepare your sql statement
			String sql;
			if (rating == -1) {
				sql = String.format("INSERT INTO imbd.user_review( show_id,user_id, review, date) VALUES('%d','%s', '%s', '%s')", showid, username, review,date.toString());
			}
			else if ((review == null) || review.isBlank()) {
				sql = String.format("INSERT INTO imbd.user_review( show_id,user_id, rating, date) VALUES('%d','%s','%d', '%s')", showid, username,rating,date.toString());
			}
			else {
				sql = String.format("INSERT INTO imbd.user_review( show_id,user_id, rating, review, date) VALUES('%d','%s','%d', '%s', '%s')", showid, username,rating, review,date.toString());
			}
			

			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			statement.execute(sql);
			DatabaseUtils.closeConnection(connection);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
