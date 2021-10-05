package app.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static app.controller.SignUpControllerDummy.checkProductionCompany;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckProductionCompanyTest {

	private Map<String, String> formParams;
	
	@BeforeEach
	public void resetFormParams() {
		formParams = new HashMap<String, String>();
	}
	
	@Test
	public void checkProductionCompany_ReturnsTrue_IfOrganisationIsExistingProductionCompany() {
		
		formParams.put("organisation", "universal pictures");
		assertTrue(checkProductionCompany(formParams));
	}
	
	@Test
	public void checkProductionCompany_ThrowsNullPointerException_IfFormParamsIsNull() {
		assertThrows(NullPointerException.class, () -> checkProductionCompany(null));
	}
	
	@Test
	public void checkProductionCompany_ThrowsNullPointerException_IfOrganisationIsNull() {
		
		formParams.put("organisation", null);
		assertThrows(NullPointerException.class, () -> checkProductionCompany(formParams));
	}
	
	@Test
	public void checkProductionCompany_ThrowsNullPointerException_IfFormParamsIsEmpty() {
		assertThrows(NullPointerException.class, () -> checkProductionCompany(formParams));
	}
	
	@Test
	public void checkProductionCompany_ReturnsFalse_IfOrganisationIsNotExistingProductionCompany() {
		
		formParams.put("organisation", "some production company");
		assertFalse(checkProductionCompany(formParams));
	}
	
	@Test
	public void checkProductionCompany_ReturnsFalse_IfOrganisationIsBlank() {
		
		formParams.put("organisation", "");
		assertFalse(checkProductionCompany(formParams));
	}
}
