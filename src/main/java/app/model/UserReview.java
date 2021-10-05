package app.model;

import java.util.Date;

import app.dao.AccountDAO;





public class UserReview {
    private int reviewID;
    private String username;
    private String review;
    private Integer rating;
    private Date date;
    private int showID;


    //@overload, to register a review when it is first created
    public UserReview(String username, int showID, String r, Integer v) {
    	this.username = username;
    	this.showID = showID;
        review = r;
        rating = v;
        date = new Date();
    }
    
    public UserReview(String username, int showID, String r, Integer v, Date date) {
    	this.username = username;
    	this.showID = showID;
        review = r;
        rating = v;
        this.date = date;
    }



    

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }
 
    public String getUserID() {
    	return username;
    }
    
    public String getPublicName() {
    	return AccountDAO.getUserByUsername(this.username).getPublicName();
    }
    
}
