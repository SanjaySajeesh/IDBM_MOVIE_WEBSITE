package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.CreditsRoll;
import app.model.Show;
import app.model.UserReview;

public class ShowDAO {
	private static List<Show> getShowGenericMethod(String sql) {
		List<Show> shows = new ArrayList<>();
		try {
			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			if (statement.execute(sql)) {
				ResultSet result = statement.getResultSet();

				// add to the list
				while (result.next()) {
					// 1) Create a new show object
					Show show = new Show(result.getString("showid"), result.getString("show_title"),
							result.getString("genre"), result.getString("length"), result.getString("movie"),
							result.getString("series"), result.getString("proco_id"), result.getString("year"),
							result.getString("author"), result.getString("entry_status"),
							result.getString("entry_status_updated"));
					for (UserReview ur : UserReviewDAO.getUserReviewsByShowId(Integer.parseInt(show.getId()))) {
						if (ur != null) {
							show.addUserReview(ur);
						}
					}
					for (CreditsRoll cr : CreditsRollDAO.getCreditRollsByShowId(Integer.parseInt(show.getId()))) {
						if (cr != null)
							show.addCreditsRoll(cr);
					}
					// 2) Add it to the list we have prepared
					shows.add(show);
				}
			}
			// Close it
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (shows.size() == 0)
			return null;
		else
			return shows;
	}

	public static List<Show> getShowByTitle(String title) {
		return getShowGenericMethod("SELECT * FROM imbd.show WHERE show_title LIKE '%" + title
				+ "' OR show_title LIKE '" + title + "%' OR show_title LIKE '%" + title + "%'");
	}

	public static Show getShowById(String showId) {
		return getShowGenericMethod("SELECT * FROM imbd.show WHERE showid = " + showId + ";").get(0);
	}

	public static List<Show> getShowByActor(String actor) {
		return getShowGenericMethod(
				"select * from imbd.show s join credits_roll cr on s.showid = cr.show_id join person p on p.person_id = cr.person_id WHERE p.fullname LIKE '%"
						+ actor + "' OR p.fullname LIKE '" + actor + "%' OR p.fullname LIKE '%" + actor + "%'");
	}

	public static List<Show> getShowByStatus(String status) {

		if (status != null) {
			return getShowGenericMethod("SELECT * FROM imbd.show WHERE entry_status = '" + status + "';");
		} else {
			return getAllShows();
		}
	}

	public static List<Show> getAllShows() {
		return getShowGenericMethod("SELECT * FROM imbd.show;");
	}

	public static void updateShowEntryStatus(String showid, String status) {
		getShowGenericMethod("UPDATE imbd.show SET entry_status = '" + status + "' WHERE showid = " + showid + ";");
	}

	public static void deleteShowEntry(String showid) {
		getShowGenericMethod("DELETE FROM imbd.show WHERE showid = " + showid + ";");
	}

	public static boolean addNewShowEntry(String title, String genre, String length, String movie, String serie,
			String proco_id, String year, String author, String status) {
		// generate unique show id
		int showId = -1;
		try {
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT max(showid) FROM imbd.show;");
			result.next();
			showId = result.getInt("max(showid)");
			showId++;

			title = title.replace("'", "\\'");

			String sql = "INSERT INTO imbd.show (showid, show_title, genre, length, movie, series, proco_id, year, author, entry_status)"
					+ "VALUES ('" + showId + "', '" + title + "', '" + genre + "', '" + length + "', '" + movie + "', '"
					+ serie + "', '" + proco_id + "', '" + year + "', '" + author + "', '" + status + "');";

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

	public static void editExistingShow(String showid, String title, String genre, String length, String movie,
			String series, String year) {
		//System.out.printf("%s %s %s %s %s %s %s",showid, title, genre, length, movie, series, year);
		editEntry("show_title", title, showid);
		editEntry("genre", genre, showid);
		editEntry("length", length, showid);
		editEntry("movie", movie, showid);
		editEntry("series", series, showid);
		editEntry("year", year, showid);

	}

	public static Double getShowAvgRating(String showid) {

		Double avgRating = null;
		try {
			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			if (statement.execute("SELECT AVG(rating) FROM imbd.user_review WHERE show_id = " + showid + ";")) {
				ResultSet result = statement.getResultSet();
				result.next();
				String avgRatingStr = result.getString("AVG(rating)");
				if (avgRatingStr != null) {
					avgRating = Double.valueOf(avgRatingStr);
				}
			}
			// Close it
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return avgRating;
	}

	public static boolean checkShowExists(String showid) {
		if (!showid.matches("\\d+")) {
			return false;
		} else {
			List<Show> result = getShowGenericMethod("SELECT * FROM imbd.show WHERE showid = " + showid + " LIMIT 1;");

			return (result != null);
		}
	}

	private static void editEntry(String key, String value, String showid) {
		try {
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			boolean result = statement.execute(
					"UPDATE imbd.show SET " + key + " = " + "'" + value + "' WHERE showid = '" + showid + "';");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
