package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.ProductionCompany;

public class ProductionCompanyDAO {
	
	 /**
     * Method to fetch production company from the database.
     * 
     * @param proco_id that identifies each production company
     * @return data of production company
     */
	private static List<ProductionCompany> getShowGenericMethod(String sql) {
		List<ProductionCompany> proco = new ArrayList<>();
		try {
			// Here you prepare your sql statement

			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			//store result in Production Company class
			while (result.next()) {
				proco.add( new ProductionCompany(result.getString("proco_name"), result.getString("proco_id")));
			}

			// Close it
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO I think this can just be 'return proco' as it should still be
		// null if there is no result
		if(proco != null) return proco;
		return null;
	}
	
	public static ProductionCompany getProCoById(String proco_id) {
		String sql = "SELECT * FROM imbd.production_company WHERE proco_id = "+proco_id+";";
		List<ProductionCompany> proco= getShowGenericMethod(sql);
		return proco.get(0);
	}
	public static List<ProductionCompany> getAllProCos() {
		String sql = "SELECT * FROM imbd.production_company;";
		return getShowGenericMethod(sql);
	}
	

}

