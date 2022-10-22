Feature: User should be able to login using correct credentials

  @practice
  Scenario: Login scenario
    Given User is on the login page of Web table app
    When User enters below credentialss
      | username | Test   |
      | password | Tester |
    Then User should see url contains orders