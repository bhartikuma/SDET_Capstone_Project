Feature: End-to-end flight booking on BlazeDemo

  # RestAssured API check har scenario se pehle chalta hai
  Background:
    Given the BlazeDemo application is reachable

  # ── Scenario 1: Direct data from feature file ──
  Scenario Outline: Book flight with inline test data
    Given I navigate to BlazeDemo homepage
    When I select "<departure>" as departure and "<destination>" as destination
    And I click on Find Flights button
    Then flight results page should be displayed
    When I select the first available flight
    Then purchase page should be displayed with flight details
    When I fill passenger details with name "<name>" city "<city>" card "<card>"
    And I click on Purchase Flight button
    Then booking confirmation should be displayed for "<name>"

    Examples:
      | departure | destination   | name         | city    | card             |
      | Boston    | London        | Rahul Sharma | Mumbai  | 4111111111111111 |

  # ── Scenario 2: Data from Excel ──
  @excel
  Scenario: Book flight using Excel test data
    Given I navigate to BlazeDemo homepage
    And I load passenger data from Excel
    When I select "Boston" as departure and "London" as destination
    And I click on Find Flights button
    Then flight results page should be displayed
    When I select the first available flight
    Then purchase page should be displayed with flight details
    When I fill passenger details from loaded data
    And I click on Purchase Flight button
    Then booking confirmation should be displayed

  # ── Scenario 3: Data from CSV ──
  @csv
  Scenario: Book flight using CSV test data
    Given I navigate to BlazeDemo homepage
    And I load passenger data from CSV
    When I select "Paris" as departure and "Buenos Aires" as destination
    And I click on Find Flights button
    Then flight results page should be displayed
    When I select the first available flight
    Then purchase page should be displayed with flight details
    When I fill passenger details from loaded data
    And I click on Purchase Flight button
    Then booking confirmation should be displayed

  # ── Scenario 4: Data from Database ──
  @database
  Scenario: Book flight using Database test data
    Given I navigate to BlazeDemo homepage
    And I load passenger data from database
    When I select "Paris" as departure and "Buenos Aires" as destination
    And I click on Find Flights button
    Then flight results page should be displayed
    When I select the first available flight
    Then purchase page should be displayed with flight details
    When I fill passenger details from loaded data
    And I click on Purchase Flight button
    Then booking confirmation should be displayed