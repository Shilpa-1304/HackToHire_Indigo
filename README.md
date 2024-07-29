# HackToHire_Indigo
A system to provide real-time flight status updates and notifications to passengers.
**TechStack:
Frontend: HTML, CSS, React.js and bootstarp.
Backend: Java with Spring boot.
Database: Mongo DB
Notifications: Kafka**

The project involves a flight status tracking application, featuring both a front-end dashboard and a back-end system for managing and updating flight information.

**Note: Please refer to the Utilities folder Flowchart, Dummy UI and Samaple dataset has been attached there for reference.**

**Back-End:** FlightStatusApplication Backend
The back-end is responsible for fetching, storing, and updating flight information. The system architecture includes the following components:

Airport Database (Airport DB): This database fetches real-time flight data every 10 minutes.
Flights Tracker Database (FlightsTracker DB): This database stores the latest flight information.
FlightStatusApplication Backend: This is the core application managing the flow of data and notifications. It involves:
Latest Flight Data: Continuously updated with new information from the Airport DB.
Flight Presence Check: Determines if a flight is already present in the FlightsTracker DB.
Change Detection: Checks for changes in cancellation status, delay status, and gate changes.
Notification System: Sends Kafka notifications to notify passengers about status changes.
Data Flow Process:
Fetching Data: The system fetches real-time flight data from the Airport DB every 10 minutes.
Database Update: If the flight is present in the FlightsTracker DB, it updates the latest changes. If not, it saves the new flight information in the repository.
Change Detection: The application checks for any changes in the flight status, delays, or gate assignments.
**Notifications:** Upon detecting any changes, the system sends notifications via Kafka to inform passengers about the status updates.
This setup ensures that passengers receive timely and accurate information about their flights, enhancing their travel experience.

**Front-End:** Flight Status Dashboard
The front-end consists of a Flight Status Dashboard that displays real-time information about various flights. The key elements of the dashboard include:

Flight Number: Identifies each flight.
Airline: The carrier operating the flight.
Origin: The departure airport.
Destination: The arrival airport.
Scheduled Departure: The planned departure time.
Scheduled Arrival: The planned arrival time.
Status: Current status of the flight (e.g., On Time, Delayed, Cancelled).
Gate: The gate from which the flight will depart or arrive.
The displayed information is up-to-date and helps passengers and other stakeholders track the status of flights easily.
