package app.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static app.controller.SignUpController.checkMatchingPassword;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckMatchingPasswordTest {

	private Map<String, String> formParams;
	
	@BeforeEach
	public void resetFormParams() {
		formParams = new HashMap<String, String>();
	}
	
	@Test
	public void checkMatchingPassword_ReturnsTrue_IfPasswordEqualsConfirmPassword() {
		
		formParams.put("password", "password");
		formParams.put("confirm_password", "password");
		assertTrue(checkMatchingPassword(formParams));
	}
	
	@Test
	public void checkMatchingPassword_ReturnsTrue_IfPasswordAndConfirmPasswordAreBlank() {
		
		formParams.put("password", "");
		formParams.put("confirm_password", "");
		assertTrue(checkMatchingPassword(formParams));
	}
	
	@Test
	public void checkMatchingPassword_ReturnsFalse_IfPasswordDoesNotEqualConfirmPassword() {
		
		formParams.put("password", "password");
		formParams.put("confirm_password", "somethingElse");
		assertFalse(checkMatchingPassword(formParams));
	}
	
	@Test
	public void checkMatchingPassword_ThrowsNullPointerException_IfPasswordIsNull() {
		
		formParams.put("confirm_password", "somethingElse");
		assertThrows(NullPointerException.class, () -> checkMatchingPassword(formParams));
	}
	
	@Test
	public void checkMatchingPassword_ThrowsNullPointerException_IfFormParamsIsNull() {
		assertThrows(NullPointerException.class, () -> checkMatchingPassword(null));
	}
	
	@Test
	public void checkMatchingPassword_ThrowsNullPointerException_IfFormParamsIsEmpty() {
		assertThrows(NullPointerException.class, () -> checkMatchingPassword(formParams));
	}
	
	@Test
	public void checkMatchingPassword_ReturnsFalse_IfConfirmPasswordIsNull() {

		formParams.put("password", "password");
		assertFalse(checkMatchingPassword(formParams));
	}
}
