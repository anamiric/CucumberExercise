* def req_headers = {X-CMC_PRO_API_KEY: 'b54bcf4d-1bca-4e8e-9a24-22ff2c3d462c'}
* def req_param = {id: '3843'}
* def req_url =  'https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/info'
Scenario: Testing valid GET endpoint
Given headers req_headers
And url req_url
And param id='3843'
When method get
Then status 200