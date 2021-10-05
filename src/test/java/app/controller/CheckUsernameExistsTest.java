package app.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static app.controller.SignUpControllerDummy.checkUsernameExists;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckUsernameExistsTest {

	private Map<String, String> formParams;
	
	@BeforeEach
	public void resetFormParams() {
		formParams = new HashMap<String, String>();
	}
	
	@Test
	public void checkUsernameExists_ReturnsTrue_IfUsernameExists() {
		
		formParams.put("username", "caramel6");
		assertTrue(checkUsernameExists(formParams));
	}
	
	@Test
	public void checkUsernameExists_ThrowsNullPointerException_IfFormParamsIsNull() {
		assertThrows(NullPointerException.class, () -> checkUsernameExists(null));
	}
	
	@Test
	public void checkUsernameExists_ReturnsFalse_IfUsernameDoesNotExist() {

		formParams.put("username", "someUsername");
		assertFalse(checkUsernameExists(formParams));
	}
	
	@Test
	public void checkUsernameExists_ReturnsFalse_IfUsernameIsNull() {

		formParams.put("username", null);
		assertFalse(checkUsernameExists(formParams));
	}
	
	@Test
	public void checkUsernameExists_ReturnsFalse_IfUsernameIsNotInMap() {
		assertFalse(checkUsernameExists(formParams));
	}
}
