$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/CalendarApp/Create_A_Meeting.feature");
formatter.feature({
  "name": "Create a recurring meeting using Native Android/iOS Calendar App.",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Create a new recurring(3 days a week) meeting, and make sure it doesnt repeat on successive days",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@calendar_app"
    }
  ]
});
formatter.step({
  "name": "I have launched the Calendar App",
  "keyword": "Given "
});
formatter.step({
  "name": "It is not a non working \u003cMeeting_Date\u003e day",
  "keyword": "When "
});
formatter.step({
  "name": "Meeting is not repeated on successive days",
  "keyword": "And "
});
formatter.step({
  "name": "I want to book a meeting with the title \u003cMeeting_Title\u003e",
  "keyword": "Then "
});
formatter.step({
  "name": "Set Meeting duration as \u003cDuration\u003e in the evening",
  "keyword": "And "
});
formatter.step({
  "name": "I invite \u003cInvitee\u003e of people",
  "keyword": "And "
});
formatter.step({
  "name": "I save the meeting",
  "keyword": "And "
});
formatter.step({
  "name": "I Check if the meeting is created as expected for \u003cMeeting_Date\u003e and \u003cMeeting_Title\u003e",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "Meeting_Date",
        "Meeting_Title",
        "Duration",
        "Invitee"
      ]
    },
    {
      "cells": [
        "2020-9-17",
        "Recurring-Team Catch Up",
        "1 Hr",
        "sonucts@gmail.com"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Create a new recurring(3 days a week) meeting, and make sure it doesnt repeat on successive days",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@calendar_app"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I have launched the Calendar App",
  "keyword": "Given "
});
formatter.match({
  "location": "CalendarHomePageSteps.aUserNavigatesToHomePage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "It is not a non working 2020-9-17 day",
  "keyword": "When "
});
formatter.match({
  "location": "CalendarHomePageSteps.getWorkingDay(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Meeting is not repeated on successive days",
  "keyword": "And "
});
formatter.match({
  "location": "CreateMeetingSteps.CreateNonRepeatativeMeeting()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to book a meeting with the title Recurring-Team Catch Up",
  "keyword": "Then "
});
formatter.match({
  "location": "CreateMeetingSteps.createMeetingTitle(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Set Meeting duration as 1 Hr in the evening",
  "keyword": "And "
});
formatter.match({
  "location": "CreateMeetingSteps.setMeetingDuration(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I invite sonucts@gmail.com of people",
  "keyword": "And "
});
formatter.match({
  "location": "CreateMeetingSteps.invitePeople(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I save the meeting",
  "keyword": "And "
});
formatter.match({
  "location": "CreateMeetingSteps.saveMeeting()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I Check if the meeting is created as expected for 2020-9-17 and Recurring-Team Catch Up",
  "keyword": "Then "
});
formatter.match({
  "location": "CreateMeetingSteps.verifyMeetingCreated(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});