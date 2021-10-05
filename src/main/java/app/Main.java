package app;

import app.controller.AccountController;
import app.controller.AdminAccountEntryListController;
import app.controller.AdminController;
import app.controller.IndexController;
import app.controller.LoginController;
import app.controller.AdminShowEntryListController;
import app.controller.EditController;
import app.controller.ShowController;
import app.controller.SignUpController;
import app.controller.SubmitShowController;
import app.controller.UserReviewController;
import app.controller.SearchController;
import app.controller.PersonController;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import static io.javalin.apibuilder.ApiBuilder.*;





public class Main {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(getHerokuAssignedPort());

        app.routes(() -> {
            // You will have to update this, to limit who can see the reviews
        	before(LoginController.ensureLoginBeforeViewing);

            get(Web.INDEX, IndexController.serveIndexPage);

            get(Web.LOGIN, LoginController.serveLoginPage);
            post(Web.LOGIN, LoginController.handleLoginPost);
            post(Web.LOGOUT, LoginController.handleLogoutPost);
            
            get(Web.SIGNUP, SignUpController.serveSignUpPage);
            post(Web.SIGNUP, SignUpController.handleSignUpType);
            get(Web.ADMIN_ACCOUNT_ENTRY_LIST, AdminAccountEntryListController.serveAdminAccountEntryList);
            post(Web.ADMIN_ACCOUNT_ENTRY_LIST, AdminAccountEntryListController.handleAdminAccountEntryUpdateStatusPost);

            get(Web.ACCOUNT, AccountController.serveAccountPage);
            
            get(Web.SUBMIT_SHOW, SubmitShowController.serveSubmitShowPage);
            post(Web.SUBMIT_SHOW, SubmitShowController.handleSubmitShowPost);
            

            post(Web.SEARCHTITLEFORM, IndexController.handleSearchShowByShowTitlePost);
            post(Web.SEARCHACTORFORM, IndexController.handleSearchShowByActorNamePost);
            get(Web.SEARCH, SearchController.serveSearchPage);
            get(Web.SHOW, ShowController.serveShowPage);
            get(Web.PERSON, PersonController.servePersonPage);
            
            get(Web.ADMIN, AdminController.serveAdminPage);
            get(Web.ADMIN_SHOW_ENTRY_LIST, AdminShowEntryListController.serveAdminShowEntryListNoFilter);
            get(Web.ADMIN_SHOW_ENTRY_LIST_FILTERED, AdminShowEntryListController.serveAdminShowEntryListFiltered);
            post(Web.ADMIN_SHOW_ENTRY_LIST_FILTERED, AdminShowEntryListController.serveAdminShowEntryListFiltered);
            post(Web.ADMIN_SHOW_ENTRY_UPDATE_STATUS, AdminShowEntryListController.handleAdminShowEntryUpdateStatusPost);
            post(Web.ADMIN_SHOW_ENTRY_UPDATE_STATUS_FROM_PAGE, AdminShowEntryListController.handleAdminShowEntryUpdateStatusFromPagePost);
            post(Web.REVIEW, UserReviewController.handlePostReview);
            
            get(Web.EDIT, EditController.serveEditShowPage);
            post(Web.EDIT, EditController.postEditShowPage);
        });

        app.error(404, ViewUtil.notFound);
    }






    public static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000;
    }



}
