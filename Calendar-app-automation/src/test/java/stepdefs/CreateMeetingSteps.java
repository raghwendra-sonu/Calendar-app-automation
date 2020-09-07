package stepdefs;

import allocator.ReusableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import listeners.ExtentReportListener;
import static stepdefs.CalendarHomePageSteps.isWorkingDate;
import static stepdefs.CalendarHomePageSteps.logInfo;
import java.io.IOException;

import com.aventstack.extentreports.GherkinKeyword;

public class CreateMeetingSteps extends ExtentReportListener {
	ReusableMethods RM = new ReusableMethods();
	public static String ObjectToken;

	@And("Meeting is not repeated on successive days")
	public void CreateNonRepeatativeMeeting() throws Exception {
		if (isWorkingDate == true) {
			logInfo = test.createNode(new GherkinKeyword("And"), "Meeting is not repeated on successive days");
			RM.PerformActionOnElement("Add_Event_Btn_HomePage", "Click", "");
			RM.PerformActionOnElement("Meeting_Frequency_CreateMeetingPage", "Click", "");
			RM.PerformActionOnElement("Select_Repeat_CreateMeetingPage", "Click", "");
			RM.PerformActionOnElement("Save_Btn_CreateMeetingPage", "Click", "");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		}
	}

	@Then("I want to book a meeting with the title (.*)")
	public void createMeetingTitle(String MeetingTitle) {
		try {
			if (isWorkingDate == true) {
				logInfo = test.createNode(new GherkinKeyword("Then"), "I book a meeting with the title "+MeetingTitle);
				RM.PerformActionOnElement("Meeting_Title_CreateMeetingPage", "Type", MeetingTitle);
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("Set Meeting duration as (.*) in the evening")
	public void setMeetingDuration(String Duration) throws IOException, ClassNotFoundException {
		if (isWorkingDate == true) {
			logInfo = test.createNode(new GherkinKeyword("And"), "I set meeting for "+Duration+" in the evening");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		}
	}

	@And("I invite (.*) of people")
	public void invitePeople(String Invitee) {
		try {
			if (isWorkingDate == true) {
				logInfo = test.createNode(new GherkinKeyword("And"), "I invite "+Invitee +" in the meeting");
				RM.PerformActionOnElement("Invitee_CreateMeetingPage", "Type", Invitee);
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("I save the meeting")
	public void saveMeeting() {
		try {
			if (isWorkingDate == true) {
				logInfo = test.createNode(new GherkinKeyword("And"), "I save the meeting");
				RM.PerformActionOnElement("Save_Btn_CreateMeetingPage", "Click", "");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("I Check if the meeting is created as expected for (.*) and (.*)")
	public void verifyMeetingCreated(String meeting_date, String meeting_title) throws Exception {
		try {
			if (isWorkingDate == true) {
				logInfo = test.createNode(new GherkinKeyword("Then"), "I verify the meeting is created as expected for "+meeting_date +" and with title " +meeting_title);
				Thread.sleep(3000);
				RM.PerformActionOnElement("MeetingDate_HomePage", "Click", meeting_date);
				RM.PerformActionOnElement("CreatedMeeting_HomePage", "ElementDisplayed", meeting_title);
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
