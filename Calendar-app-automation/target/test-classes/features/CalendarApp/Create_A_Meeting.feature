Feature: Create a recurring meeting using Native Android/iOS Calendar App.

@calendar_app
Scenario Outline: Create a new recurring(3 days a week) meeting, and make sure it doesnt repeat on successive days
	Given I have launched the Calendar App
  When It is not a non working <Meeting_Date> day
	And Meeting is not repeated on successive days
	Then I want to book a meeting with the title <Meeting_Title>
	And Set Meeting duration as <Duration> in the evening
	And I invite <Invitee> of people
	And I save the meeting
	Then I Check if the meeting is created as expected for <Meeting_Date> and <Meeting_Title>

Examples:
	|Meeting_Date |Meeting_Title            |Duration | Invitee          |        
	|2020-9-17    |Recurring-Team Catch Up  |1 Hr     | sonucts@gmail.com|