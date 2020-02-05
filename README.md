Dear User,

This project will help you to schedule events between 9:00AM to 5:00PM for given list of events.
For more events, it will span across consecutive schedules.
Refer input files at test resources folder. (EventInput.txt, EventInput2.txt for positive cases and rest for negative cases).

To run:
Just import as maven project and run with maven build command "mvn clean test".

To note:
1. Avoided using log4j and used sysout instead for keeping the project simple. I am aware of logging frameworks.
2. ScheduleProcessor is called in test method in ScheduleProcessorTest and it just prints the Events.
   For actual implementation, this processor can be called to get the list of Schedules and process as per requirement. 

