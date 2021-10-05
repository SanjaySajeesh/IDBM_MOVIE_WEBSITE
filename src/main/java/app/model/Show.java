package app.model;

import java.util.ArrayList;
import java.util.List;

import app.dao.AccountDAO;
import app.dao.ProductionCompanyDAO;

public class Show {
	
	// Valid values for entry_status:
	public static final String VISIBLE = "VISIBLE";
	public static final String SUBMITTED = "SUBMITTED";
	public static final String UNDER_INVESTIGATION = "UNDER INVESTIGATION";
	public static final String SUSPENDED = "SUSPENDED";
	public static final String PENDING = "PENDING";
	public static final String REJECTED = "REJECTED";
	
	private int showid;
	private String showTitle;
	private double length;
	private boolean isMovie;
	private boolean isSeries;
	private String genre;
	private int year;

	private List<UserReview> userReviewList = new ArrayList<UserReview>();
	private ProductionCompany productionCompany;

	private List<CreditsRoll> creditsRolls = new ArrayList<CreditsRoll>();
	
	private Account author;
	private String entry_status;
	private String entry_status_updated;

	
	public Show(String showid, String showTitle, String genre, String length, String movie, String series,
			String proco_id, String year, String author, String entry_status, String entry_status_updated) {		
		
		this.showid = Integer.parseInt(showid);
		this.showTitle = showTitle;
		this.genre = genre;
		this.length = Double.parseDouble(length);
		isMovie = (1 == Integer.parseInt(movie));
		isSeries = (1 == Integer.parseInt(series));
		productionCompany = ProductionCompanyDAO.getProCoById(proco_id);
		this.year = Integer.parseInt(year);	
		
		this.author = (author != null) ? AccountDAO.getUserByUsername(author)
				: new Account("", "", "", "", "", "", "", "", "");
		this.entry_status = entry_status;
		this.entry_status_updated = entry_status_updated;
	}

	public String getTitle() {
		return showTitle;
	}
	public String getId() {
		return String.valueOf(showid);
	}
	public String getGenre() {
		return genre;
	}
	public String getYear() {
		return String.valueOf(year);
	}
	public String getProCo() {
		return productionCompany.getName();
	}

	public String getPoster() {
		return "/img/shows/"+showid+".jpg";
	}
	public List<CreditsRoll> getCreditRolls() {
		return creditsRolls;
	}
	public List<UserReview> getUserReviews(){
		return userReviewList;
	}
	
	public String getLength() {
		int hours = (int)length;
		int min = (int) Math.round((length-hours) * 60);
		return hours + "hours "+ min + "mins";
	}
	public Boolean isMovie() {
		return isMovie;
	}
	public Boolean isSeries() {
		return isSeries;
	}

	public Account getAuthor() {
		return this.author;
	}
	
	public String getEntryStatus() {
		return this.entry_status;
	}
	
	public String getEntryStatusUpdated() {
		return this.entry_status_updated;
	}
	public void setEntryStatus(String status) {		
		this.entry_status = status;
	}
	
	public boolean addCreditsRoll(CreditsRoll cr) {
		return creditsRolls.add(cr);
	}
	
	public boolean addUserReview(UserReview ur) {
		return userReviewList.add(ur);
	}
	
	public int getHours() {
		return (int) length;
	}
	
	public int getMinutes() {
		return (int)(length * 60 % 60);
	}

}
