package app.controller;

import static app.controller.SignUpControllerDummy.checkSubmissionToDatabase;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckSubmissionToDatabaseTest {

	private Map<String, String> formParams;
	
	@BeforeEach
	public void resetFormParams() {
		formParams = new HashMap<String, String>();
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsTrue_IfFormParamsAreValid() {
		
		formParams.put("username", "username");
		formParams.put("password", "password");
		formParams.put("firstname", "firstname");
		formParams.put("lastname", "lastname");
		formParams.put("gender", "neutral");
		formParams.put("year", "1999");
		formParams.put("country", "country");
		formParams.put("postalcode", "1234");
		formParams.put("email", "email");
		formParams.put("organisation", "organisation");
		formParams.put("phoneNum", "12345678");
		formParams.put("type", "PCO");
		
		assertTrue(checkSubmissionToDatabase(formParams));
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsFalse_IfAllFormParamsAreInvalid() {
		
		formParams.put("username", null);
		formParams.put("password", null);
		formParams.put("firstname", null);
		formParams.put("lastname", null);
		formParams.put("gender", null);
		formParams.put("year", "abc");
		formParams.put("country", null);
		formParams.put("postalcode", "abc");
		formParams.put("email", null);
		formParams.put("organisation", null);
		formParams.put("phoneNum", "abc");
		formParams.put("type", null);
		
		assertFalse(checkSubmissionToDatabase(formParams));
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsFalse_IfFormParamsIsEmpty() {
		assertFalse(checkSubmissionToDatabase(formParams));
	}
	
	@Test
	public void checkSubmissionToDatabase_ThrowsNullPointerException_IfFormParamsIsNull() {
		assertThrows(NullPointerException.class, () -> checkSubmissionToDatabase(null));
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsFalse_IfUsernameIsNull() {
		
		formParams.put("username", null);
		formParams.put("password", "password");
		formParams.put("firstname", "firstname");
		formParams.put("lastname", "lastname");
		formParams.put("gender", "neutral");
		formParams.put("year", "1999");
		formParams.put("country", "country");
		formParams.put("postalcode", "1234");
		formParams.put("email", "email");
		formParams.put("organisation", "organisation");
		formParams.put("phoneNum", "12345678");
		formParams.put("type", "PCO");
		
		assertFalse(checkSubmissionToDatabase(formParams));
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsFalse_IfFirstnameIsNull() {
		
		formParams.put("username", "username");
		formParams.put("password", "password");
		formParams.put("firstname", null);
		formParams.put("lastname", "lastname");
		formParams.put("gender", "neutral");
		formParams.put("year", "1999");
		formParams.put("country", "country");
		formParams.put("postalcode", "1234");
		formParams.put("email", "email");
		formParams.put("organisation", "organisation");
		formParams.put("phoneNum", "12345678");
		formParams.put("type", "PCO");
		
		assertFalse(checkSubmissionToDatabase(formParams));
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsFalse_IfLastnameIsNull() {
		
		formParams.put("username", "username");
		formParams.put("password", "password");
		formParams.put("firstname", "firstname");
		formParams.put("lastname", null);
		formParams.put("gender", "neutral");
		formParams.put("year", "1999");
		formParams.put("country", "country");
		formParams.put("postalcode", "1234");
		formParams.put("email", "email");
		formParams.put("organisation", "organisation");
		formParams.put("phoneNum", "12345678");
		formParams.put("type", "PCO");
		
		assertFalse(checkSubmissionToDatabase(formParams));
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsFalse_IfGenderIsNull() {
		
		formParams.put("username", "username");
		formParams.put("password", "password");
		formParams.put("firstname", "firstname");
		formParams.put("lastname", "lastname");
		formParams.put("gender", null);
		formParams.put("year", "1999");
		formParams.put("country", "country");
		formParams.put("postalcode", "1234");
		formParams.put("email", "email");
		formParams.put("organisation", "organisation");
		formParams.put("phoneNum", "12345678");
		formParams.put("type", "PCO");
		
		assertFalse(checkSubmissionToDatabase(formParams));
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsFalse_IfCountryIsNull() {
		
		formParams.put("username", "username");
		formParams.put("password", "password");
		formParams.put("firstname", "firstname");
		formParams.put("lastname", "lastname");
		formParams.put("gender", "neutral");
		formParams.put("year", "1999");
		formParams.put("country", null);
		formParams.put("postalcode", "1234");
		formParams.put("email", "email");
		formParams.put("organisation", "organisation");
		formParams.put("phoneNum", "12345678");
		formParams.put("type", "PCO");
		
		assertFalse(checkSubmissionToDatabase(formParams));
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsFalse_IfEmailIsNull() {
		
		formParams.put("username", "username");
		formParams.put("password", "password");
		formParams.put("firstname", "firstname");
		formParams.put("lastname", "lastname");
		formParams.put("gender", "neutral");
		formParams.put("year", "1999");
		formParams.put("country", "country");
		formParams.put("postalcode", "1234");
		formParams.put("email", null);
		formParams.put("organisation", "organisation");
		formParams.put("phoneNum", "12345678");
		formParams.put("type", "PCO");
		
		assertFalse(checkSubmissionToDatabase(formParams));
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsFalse_IfPhoneNumNotAnIntegerValue() {
		
		formParams.put("username", "username");
		formParams.put("password", "password");
		formParams.put("firstname", "firstname");
		formParams.put("lastname", "lastname");
		formParams.put("gender", "neutral");
		formParams.put("year", "1999");
		formParams.put("country", "country");
		formParams.put("postalcode", "1234");
		formParams.put("email", "email");
		formParams.put("organisation", "organisation");
		formParams.put("phoneNum", "abc");
		formParams.put("type", "PCO");
		
		assertFalse(checkSubmissionToDatabase(formParams));
	}
	
	@Test
	public void checkSubmissionToDatabase_ReturnsFalse_IfPostcodeNotAnIntegerValue() {
		
		formParams.put("username", "username");
		formParams.put("password", "password");
		formParams.put("firstname", "firstname");
		formParams.put("lastname", "lastname");
		formParams.put("gender", "neutral");
		formParams.put("year", "1999");
		formParams.put("country", "country");
		formParams.put("postalcode", "abc");
		formParams.put("email", "email");
		formParams.put("organisation", "organisation");
		formParams.put("phoneNum", "12345678");
		formParams.put("type", "PCO");
		
		assertFalse(checkSubmissionToDatabase(formParams));
	}
}
