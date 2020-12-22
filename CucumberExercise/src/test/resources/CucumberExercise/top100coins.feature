@Top100Coins
Feature: Top 100
    Verifying that top 100 records are shown when Coins option is chosen from Cryptocurrencies section
Scenario: Top 100 cryptocurrencies are displayed
    Given I am on the homepage
    When I choose Coins from Cryptocurrencies section
    Then it should be displayed top 100 cryptocurrencies for coins