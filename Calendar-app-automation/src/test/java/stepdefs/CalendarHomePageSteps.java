package stepdefs;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import allocator.ReusableMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import listeners.ExtentReportListener;

public class CalendarHomePageSteps extends ExtentReportListener {
	ReusableMethods RM = new ReusableMethods();
	public static boolean isWorkingDate = false;
	public static String ObjectToken;
	public static ExtentTest logInfo = null;

	@Given("I have launched the Calendar App")
	public void aUserNavigatesToHomePage() throws ClassNotFoundException {
		test = extent.createTest(Feature.class, "Calendar App Verifications");
		test = test.createNode(Scenario.class, "Verify Meeting Creation in Calendar App");
		logInfo = test.createNode(new GherkinKeyword("Given"), "I launch the Calendar App");
		try {
			if (RM.waitForCondition("Presence", "Add_Event_Btn_HomePage", 10)) {
				logInfo.pass("Launched Calendar Application Successfully");
			} else {
				logInfo.fail("Unable to launch Calendar Application");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

	@When("It is not a non working (.*) day")
	public void getWorkingDay(String meetingDate) {
		try {
			logInfo = test.createNode(new GherkinKeyword("When"), "Verify that it is not a non working day");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			if (RM.isWorkingDay(meetingDate) == true) {
				logInfo.pass(meetingDate+" is a working day");
				isWorkingDate = true;
				RM.PerformActionOnElement("MeetingDate_HomePage", "Click", meetingDate);
			}
			System.out.println("isWorkingDate? = " + isWorkingDate);
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}
}