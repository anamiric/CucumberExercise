@Watchlist
Feature: Watchlist check
  Verifying that all added cryptocurrencies are displayed in Watchlist page
  Scenario: All added cryptocurrencies are displayed
    Given user is on the homepage
    When I click on Filters button
    And enter Price range from 23000-99999 and click Apply button
    And add all filtered cryptocurrencies to Watchlist
    And click on Watchlist tab
    Then all added cryptocurrencies should be displayed