 Feature: Get all products
#   Scenario: Verify the get api for products
#     Given I invoke the baseURL
#     When I pass the url of the products in the request "products"
#     Then I received the code as 200

     Scenario: Verify the rate of a product
       Given I invoke the baseURL
     When I pass the url of the products in the request "products"
       Then Product rate is <ProductRate>
#       And The first product is <Category>
       Example:
         |ProductRate|Category|
         |3.9| "men's clothing"|