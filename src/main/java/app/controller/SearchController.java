package app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.PersonDAO;
import app.dao.ShowDAO;
import app.model.Person;
import app.model.Show;
import io.javalin.http.Handler;

public class SearchController {
	private static String searchType = null;
	private static String showTitle = null;
	private static String actorName = null;

	// Search for shows from a keyword
	public static Handler serveSearchPage = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		if (searchType == null) {
			showTitle = null;
			actorName = null;
	        ctx.render(Template.NOT_FOUND, model);
		} else {
			processSearch(model);
			ctx.render(Template.SEARCH, model);
		}
	};

	// get search results and put in model
	private static void processSearch(Map<String, Object> model) {

		switch (searchType) {
			case Web.SEARCHTITLEFORM: {
				model.put("showTitle", showTitle);
				List<Show> results = prepareSearchByTitleResults();
				if ((results != null) && (!results.isEmpty())) {
					model.put("searchResults", results);
				}
				showTitle = null;// resetting the value for next search
				break;
			}

			case Web.SEARCHACTORFORM: {
				model.put("actorName", actorName);
				Map<Person, List<Show>> results = prepareSearchByActorResults();
				if (!results.isEmpty()) {
					model.put("searchResults", results.entrySet());
				}
				actorName = null;// resetting the value for next search
				break;
			}
		}
		searchType = null;// resetting the value for next search
	}
	
	private static List<Show> prepareSearchByTitleResults() {
		
		List<Show> results = ShowDAO.getShowByTitle(showTitle);
		if (results != null) {
			results = checkSearchByTitleResults(results);
		}
		return results;
	}
	
	private static List<Show> checkSearchByTitleResults(List<Show> results) {

		if (EntryStatusController.checkSubmittedPendingBeforeView(results)) {
			results = ShowDAO.getShowByTitle(showTitle);
		}
		results = removeNonVisibleResults(results);
		return results;
	}
	
	private static Map<Person, List<Show>> prepareSearchByActorResults() {
		
		List<Person> actorResults = PersonDAO.getPersonByName(actorName);
		Map<Person, List<Show>> results = new HashMap<Person, List<Show>>();

		for (Person actor : actorResults) {
			List<Show> result = actor.getShowStarringByPerson();
			if (result != null) {
				results.put(actor, result);
			}
		}
		checkSearchByActorResults(results);
		return results;
	}
	
	private static Map<Person, List<Show>> checkSearchByActorResults(Map<Person, List<Show>> results) {
		
		List<Person> resultsToRemove = new ArrayList<Person>();
		for (Map.Entry<Person, List<Show>> result : results.entrySet()) {
			if (EntryStatusController.checkSubmittedPendingBeforeView(result.getValue())) {
				results.put(result.getKey(), result.getKey().getShowStarringByPerson());
			}
			results.put(result.getKey(), removeNonVisibleResults(result.getValue()));
			if (result.getValue().isEmpty()) {
				resultsToRemove.add(result.getKey());
			}
		}
		for (Person resultToRemove : resultsToRemove) {
			results.remove(resultToRemove);
		}
		return results;
	}

	private static List<Show> removeNonVisibleResults(List<Show> results) {

		List<Show> resultsToRemove = new ArrayList<Show>();

		for (Show result : results) {
			if (!Show.VISIBLE.equals(result.getEntryStatus())) {
				resultsToRemove.add(result);
			}
		}

		for (Show resultToRemove : resultsToRemove) {
			results.remove(resultToRemove);
		}

		return results;
	}

	// store searched keyword
	public static void setShowTitle(String getQueryTitle) {
		searchType = Web.SEARCHTITLEFORM;
		showTitle = getQueryTitle;
	}

	// store searched actorName
	public static void setActorName(String getQueryActor) {
		searchType = Web.SEARCHACTORFORM;
		actorName = getQueryActor;
	}
}
