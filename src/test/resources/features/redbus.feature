Feature: RedBus Booking Flow

  Scenario: Search bus and select seat
    Given user opens the browser
    When user opens redBus application
    And user enters source "Bangalore"
    And user enters destination "Chennai"
    And user selects journey date
    And user clicks on search
    And user selects a bus
    Then user selects a seat
