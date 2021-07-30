Feature: Ejercicio con Cucumber


Scenario: Verify Inputs Header

Given User is on Header Tab 

When User Creates New Header Form

Then Assert that titles and field types present in Form matches with expected in "TypeFieldsHeader.xlsx"



Scenario: Create Header

Given User is on Header Tab 

When User Creates New Header Form

Then Error should be displayed when submitting no data 

And Error should be displayed when submitting with missing data

And Header created succesfullly when submitting with all fields completed



Scenario: Verify Inputs Body Record

Given User is in WSR Body Record Tab 

When User Creates  New WSR Body Form 

Then Assert that titles and field types present in Form matches with expected in excel "WSRBodyRecordTypes.xlsx"



Scenario: Create WSR Body Record

Given User is in WSR Body Record Tab 

When User Creates and fills New WSR Body Form 

Then Error should be displayed when leaving work done field empty

And Error should be displayed when hours >8

And Error should be displayed when hours <8

And Error should be displayed when amount of stories doesn`t match with stories in completed, in progress and not started.

And Error should be displayed when sprint start date = sprint end date

And Error should be displayed when sprint start date > sprint end date

And WSR Body created succesfullly with all fields completed with data form Excel




Scenario: Send to manager

Given User is in WSR Body Record Tab 

And Email Owner is created 

When User click in Submit to manager

Then Record should be sent succesfully



 