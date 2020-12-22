Feature: Cryptocurrency info

  Background:
    * url 'https://sandbox-api.coinmarketcap.com'
    * header X-CMC_PRO_API_KEY = 'b54bcf4d-1bca-4e8e-9a24-22ff2c3d462c'
    * param id = '3843'

  Scenario: check cryptocurrency symbol
    Given path '/v1/cryptocurrency/info'
    When method get
    Then status 200
    And match $.data.3843.symbol == "BOLT"

  Scenario: check error message is null
    Given path '/v1/cryptocurrency/info'
    When method get
    Then status 200
    And match $.status.error_message == "#null"

  Scenario: check source code github url present
    Given path '/v1/cryptocurrency/info'
    When method get
    Then status 200
    And match $.data.3843.urls.source_code contains "https://github.com/SyQic-Ops/bolt"

  Scenario: check date
    Given path '/v1/cryptocurrency/info'
    When method get
    Then status 200
    And match $.data.3843.date_added == "2019-04-05T00:00:00.000Z"

  Scenario: check logo URL is present
    Given path '/v1/cryptocurrency/info'
    When method get
    Then status 200
    And match $.data.3843.logo contains "https://s2.coinmarketcap.com/static/img/coins/64x64/3843.png"

  Scenario: check website url is present
    Given path '/v1/cryptocurrency/info'
    When method get
    Then status 200
    And match $.data.3843.urls.website contains "https://bolt.global/"

