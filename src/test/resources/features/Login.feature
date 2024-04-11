Feature: Login feature

  Scenario: Login Success
    Given I open Login Page
    When I enter email "burul.satybaeva@testpro.io"
    And I enter password "Lulkerup7710%"
    And I click Submit
    Then I am logged in
