package app.model;



import java.util.Date;
import java.util.List;

import app.dao.ShowDAO;



public class Person {
    private int personId;
    private String fullName;
    private String role;
    private String bio;
    private Date birthdate;




    public Person(int id, String fn, String r, Date bd, String b) {
        personId = id;
        fullName = fn;
        role = r;
        birthdate = bd;
        bio = b;
    }



    public String getRole() {
        return role;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPersonId() {
        return personId;
    }

    public String getBio() {
        return bio;
    }
    public String getImg() {
    	return "/img/people/"+personId+".jpg";
    }
    
    public List<Show> getShowStarringByPerson(){
    	List<Show> results = ShowDAO.getShowByActor(getFullName());
    	return results;
    }
    
    public boolean starredAnyShows() {
    	if (getShowStarringByPerson() != null) {
    		return true;
    	}
    	return false;
    }
}
