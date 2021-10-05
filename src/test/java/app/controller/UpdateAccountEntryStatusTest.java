package app.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static app.controller.EntryStatusControllerDummy.resetTestPassed;
import static app.controller.EntryStatusControllerDummy.getTestPassed;
import static app.controller.EntryStatusControllerDummy.updateAccountEntryStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateAccountEntryStatusTest {
	
	@BeforeEach
	public void resetTestResult() {
		resetTestPassed();
	}
	
	@Test
	public void updateAccountEntryStatus_Passes_IfStatusIsApproveAndUsernameExists() {
		updateAccountEntryStatus("Approve", "caramel6");
		assertTrue(getTestPassed());
	}
	
	@Test
	public void updateAccountEntryStatus_Passes_IfStatusIsRejectDeleteAndUsernameExists() {
		updateAccountEntryStatus("Reject/Delete", "admin");
		assertTrue(getTestPassed());
	}
	
	@Test
	public void updateAccountEntryStatus_Fails_IfStatusIsInvalid() {
		updateAccountEntryStatus("someStatus", "caramel6");
		assertFalse(getTestPassed());
	}
	
	@Test
	public void updateAccountEntryStatus_Fails_IfUsernameDoesNotExist() {
		updateAccountEntryStatus("Approve", "someUsername");
		assertFalse(getTestPassed());
	}
}
