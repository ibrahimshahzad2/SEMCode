## USE CASE: 1 Sort Geographical Places by Population
## CHARACTERISTIC INFORMATION
## Goal in Context
The end user must be able to sort the countries of the world, continents, cities and region by population size.

## Scope
Global Data System

## Level
Primary task.

## Preconditions
The system should have access to the current population data for countries of the world, continents and region. The end user should have access to the sorting interface 

## Success End Condition
It should be displaying the list of the geographical entities and is sorted accurately.

## Failed End Condition
List wasn’t producing due to the sorting order not being done correctly

## Primary Actor:
End User

## MAIN SUCCESS SCENARIO:
1.	End User selects the geographic scope (e.g., "Countries of the World," "All                       Continents," "A Specific Region").
2.	End User selects the "Population" attribute as the sorting criteria.
3.	End User selects the desired sorting oRder (Ascending or Descending).
4.	The system executes the sorting operation on the list.
5.	The system displays the sorted list to the End User.

## Extensions:
1.	No data available for selected scope
a.	System displays an error message: “No population data available for the selected scope”
b.	Use case ends

2.	Sorting fails (e.g. data inconsistency)
a.	System displays an error message: “Sorting failed due to data error. Please try again”
b.	Use case ends.

SUB-VARIATIONS: 
Selecting geographic scope: The End User may choose to filter the list further (e.g., sort European countries by population). The system applies the filter before sorting. Sorting order selection: The system may default to Descending order (highest population first), which the End User can accept or chang
