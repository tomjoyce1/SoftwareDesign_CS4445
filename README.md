# SoftwareDesign_CS4445

| Name                        | Student Number |
| --------------------------- | -------------- |
| Thomas Joyce                | 23367857       |
| Nandakishore Vinayakrishnan | 23070854       |
| Dara Heaphy                 | 23369914      |
| Tiernan Scully              | 23365528       |

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=tomjoyce1_SoftwareDesign_CS4445&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=tomjoyce1_SoftwareDesign_CS4445)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=tomjoyce1_SoftwareDesign_CS4445&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=tomjoyce1_SoftwareDesign_CS4445)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=tomjoyce1_SoftwareDesign_CS4445&metric=coverage)](https://sonarcloud.io/summary/new_code?id=tomjoyce1_SoftwareDesign_CS4445)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=tomjoyce1_SoftwareDesign_CS4445&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=tomjoyce1_SoftwareDesign_CS4445)


===============================
ISE International Airport Simulator
===============================

This project simulates an airport environment with various flight types, takeoff scheduling, 
and collision detection. Below is a one‐line summary of the main packages and classes:

1. Package: models.map
   - Contains classes representing the airport layout and its individual cells.
     • AirTrafficMap – Manages the grid of MapCell objects, including airport designation and flight placement.
     • MapCell – Represents a single cell on the airport map with properties such as airport flag, lock status, and stored flights.

2. Package: models.pathfinder
   - Provides pathfinding functionality for the simulation.
     • PathFinder – Implements Dijkstra’s algorithm to calculate the shortest path between two cells on the AirTrafficMap.

3. Package: utils
   - Offers helper classes for input parsing, flight lookup, and airport selection.
     • InputParserUtil – Parses user string input into numeric values.
     • FlightLookupUtil – Finds and returns a flight given its flight number.
     • AirportSelectionUtil – Assists in selecting an available airport cell based on user input.

6. Package: models.map.takeoff
   - Handles simulation aspects related to flight takeoffs and scheduling.
     • FlightSimulator – Simulates the movement of flights from takeoff through taxiing to landing.
     • ScheduledFlight – Represents a flight scheduled for takeoff, storing its source and destination coordinates.

7. Class: flight.Flight
   - The abstract base class for all flight types in the simulation.
     • Flight – Defines common flight properties (like flight number, fuel, and state) and methods for state transitions (take off, land, hold).

8. Class: models.CollisionDetector
   - Monitors the AirTrafficMap for potential flight collisions.
     • CollisionDetector – Checks for collisions between scheduled flights and, if detected, logs errors and updates flight states accordingly.

--------------------------------------------------
For additional details on each class or package, please refer to the source code documentation.