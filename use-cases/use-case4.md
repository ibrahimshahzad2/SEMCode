# USE CASE: 4 Display Population based on their countries language

## CHARACTERISTIC INFORMATION

## Goal in Context:
As an End User I want to display the population of Chinese, English, Hindi, Spanish, and Arabic speakers so that I can understand the global reach and prevalence of these major languages.

## Scope:
Global Data System (Linguistics/Demographics).

## Level:
Primary task

## Preconditions:
The system has access to current data on the number of speakers for the target languages (Chinese, English, Hindi, Spanish, Arabic).

## Success End Condition:
The system displays the total population figure for speakers of the requested language.

## Failed End Condition:
The language speaker data cannot be retrieved or displayed.

## Primary Actor:
End User.

## Trigger:
The End User asks a population query for a specific language (e.g., "How many people speak Spanish?").

## MAIN SUCCESS SCENARIO:

1.	End User selects or inputs the name of the desired language (from the supported list: Chinese, English, Hindi, Spanish, Arabic).
2.	The system retrieves the total estimated number of speakers for that language globally.
3.	The system displays the population figure to the End User.




## EXTENSIONS:

Language is not in the supported list:
•	System displays a message: "Population data is currently only available for: [List of supported languages]."
•	Use Case ends.

Missing data for the requested language:
•	System displays an error message: "Population speaker data is currently unavailable for [Language]."
•	Use Case ends.

SUB-VARIATIONS:
None.
