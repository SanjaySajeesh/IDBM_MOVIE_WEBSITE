package app.controller.paths;


/**
 * his class contains the UTL fragments of the site. If you are going to login, then the
 * URL is going to be https://someurl.com/login
 *
 * If you want to add pages, you need to add that information here.
 */
public class Web {

    public static final String INDEX = "/";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String ACCOUNT = "/account";
    
    public static final String SIGNUP = "/sign_up";
    public static final String ADMIN_ACCOUNT_ENTRY_LIST = "/admin/accountEntryList";


    
    public static final String SEARCH = "/search";
    public static final String SEARCHTITLEFORM = "/searchTitleForm";
    public static final String SEARCHACTORFORM = "/searchActorForm"; 
    public static final String SHOW = "/show/:showid";
    public static final String PERSON = "/person/:person_id";

    public static final String ADMIN = "/admin";
    public static final String ADMIN_SHOW_ENTRY_LIST = "/admin/showEntryList";
    public static final String ADMIN_SHOW_ENTRY_LIST_FILTERED = "/admin/showEntryListFiltered";    
    public static final String ADMIN_SHOW_ENTRY_UPDATE_STATUS = "/admin/showEntryUpdateStatus";
    public static final String ADMIN_SHOW_ENTRY_UPDATE_STATUS_FROM_PAGE = "/admin/showEntryUpdateStatusFromPage";
    public static final String SUBMIT_SHOW = "/submit_show";
    public static final String REVIEW = "/leaveReview";
    
    public static final String EDIT = "/edit/:showid";
}
