Dear User,

This project will help you to schedule events between 9:00AM to 5:00PM for given list of events.
For more events, it will span across consecutive schedules.
Refer input files at test resources folder. (EventInput.txt, EventInput2.txt for positive cases and rest for negative cases).

Definitions:
Schuedle meaning as List of Events with proposed StartTime. Each schedule given a int value as counter.
Event meaning as an activity on given topic(name) and duration. Once Scheduled, it will have a start Time on a Schedule.

To run:
Just import as maven project and run with maven build command "mvn clean test".
You should be able to find the events printed in console for the given input in EventInput.txt

To note:
1. Avoided using log4j and used sysout instead for keeping the project simple. I am aware of logging frameworks.
2. ScheduleProcessor is called in test method in ScheduleProcessorTest and it just prints the Events.
   For actual implementation, this processor can be called to get the list of Schedules and process as per requirement. 

