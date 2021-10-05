package app.controller;

import java.util.Calendar;
import java.util.List;

import app.dao.AccountDAO;
import app.dao.ShowDAO;
import app.model.Account;
import app.model.Show;
import io.javalin.http.Context;

public class EntryStatusController {
	
    public static boolean checkShowEntryStatusBeforeView(Context ctx, Show show) {
    	
    	switch (show.getEntryStatus()) {
    		case Show.VISIBLE:
    			return true;
    			
    		case Show.SUBMITTED:
    			return checkShowEntryViewPermissions(ctx, show);
    			
    		case Show.PENDING:
    			return checkShowEntryViewPermissions(ctx, show);
    			
    		// When "UNDER INVESTIGATION", "SUSPENDED", or "REJECTED", only show
    		// to admins and author of page, with header displaying status.
    		default:
    			return checkAdminOrAuthor(ctx, show);
    	}
    }
    
    private static boolean checkShowEntryViewPermissions(Context ctx, Show show) {
    	
    	if (checkEntryStatusSubmittedPending(show)) {
			ShowController.setEntryStatusChanged();
    		return true;
		}
		else {
			return checkAdminOrAuthor(ctx, show);
		}
    }
    
    public static boolean checkEntryStatusSubmittedPending(Show show) {
    	
    	// If 24 hours have passed, set entry_status to "VISIBLE" and return true
    	if (checkEntryStatusUpdateTimestamp(show)) {
			ShowDAO.updateShowEntryStatus(show.getId(), Show.VISIBLE);
			return true;
		}
		else {
			return false;
		}
    }
    
    // Returns true if 24 hours have passed since entry_status was last updated
    private static boolean checkEntryStatusUpdateTimestamp(Show show) {
    	
    	String ts = show.getEntryStatusUpdated();
    	if (ts == null) {
    		return false;
    	}
    	
    	String[] tsSplit = ts.split(" ");
    	String[] tsDate = tsSplit[0].split("-");
    	String[] tsTime = tsSplit[1].split(":");
    	
    	int year = Integer.parseInt(tsDate[0]);
    	int month = Integer.parseInt(tsDate[1]) - 1;
    	int date = Integer.parseInt(tsDate[2]);
    	int hourOfDay = Integer.parseInt(tsTime[0]);
    	int minute = Integer.parseInt(tsTime[1]);
    	int second = Integer.parseInt(tsTime[2]);
    	
    	Calendar nowCal = Calendar.getInstance();
    	Calendar tsCal = Calendar.getInstance();
    	tsCal.set(year, month, date, hourOfDay, minute, second);
    	
    	// if nowCal - tsCal > 24 hours
    	if ((nowCal.getTime().getTime() - tsCal.getTime().getTime()) > 86400000) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    private static boolean checkAdminOrAuthor(Context ctx, Show show) {
		
		String currentUser = ctx.sessionAttribute("currentUser");
		String currentUserType = ctx.sessionAttribute("currentUserType");
		
		if (Account.ADMIN.equals(currentUserType) || ((currentUser != null)
				? show.getAuthor().getUsername().equals(currentUser) : false)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void updateEntryStatus(Context ctx) {
    	String status = ctx.formParam("statusUpdate");
		String showid = ctx.formParam("showid");
		
		switch (status) {
			case "Approve":
				ShowDAO.updateShowEntryStatus(showid, Show.VISIBLE);
				break;
				
			case "Reject":
				ShowDAO.updateShowEntryStatus(showid, Show.REJECTED);
				break;
				
			case "Mark Pending":
				ShowDAO.updateShowEntryStatus(showid, Show.PENDING);
				break;
				
			case "Suspend":
				ShowDAO.updateShowEntryStatus(showid, Show.SUSPENDED);
				break;
				
			case "Delete":
				ShowDAO.deleteShowEntry(showid);
				break;
		}
    }
	
	public static boolean checkSubmittedPendingBeforeView(List<Show> shows) {

		boolean dbUpdated = false;

		for (Show show : shows) {
			if (Show.SUBMITTED.equals(show.getEntryStatus())
					|| Show.PENDING.equals(show.getEntryStatus())) {

				if (EntryStatusController.checkEntryStatusSubmittedPending(show)) {
					dbUpdated = true;	
				}				
			}
		}
		return dbUpdated;
	}
	
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
