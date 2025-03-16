package stepDefinitions;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.util.JSONPObject;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.Assert;


public class Products {
    public  RequestSpecification httpRequest;
    public Response response;
    public ResponseBody responseBody;
    public int statusCode;
    String baseURL = "https://fakestoreapi.com/";

        // Invoking a GET Method
    @Given("I invoke the baseURL")
    public void i_invoke_the_base_url() {
            RestAssured.baseURI = "https://fakestoreapi.com/";
    }



    @When("^I pass the url of the products in the request {}$")
    public void i_pass_the_url_of_the_products_in_the_request(String endpoint) {
//        httpRequest = RestAssured.given();
        response = RestAssured.given().get(endpoint);
    }

    @Then("I received the code as {int}")
    public void i_received_the_code_as(int int1) {
        // Write code here that turns the phrase above into concrete actions
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,int1);
        System.out.println("StatusCode:" +int1);
    }

//Get a parameter value of the response
@Then("Product rate is <ProductRate>")
public void product_rate_is_product_rate(String rate) {
//        Capture the response body
        responseBody =  response.getBody();

        //Convert the response body into a String
        String body = responseBody.asString();

        // JSON Representation of the Response
        JsonPath jsonPath = response.jsonPath();

        // Read the value of Rating of the Nth product - read from the array
        String s = jsonPath.getJsonObject("rating[0].rate").toString();
        Assert.assertEquals(rate,s);


    }

    @Then("The first product is <Category>")
    public void the_first_product_is_category(String prodCategory) {
        //        Capture the response body
        responseBody =  response.getBody();

        // JSON Representation of the Response
        JsonPath jsonPath = response.jsonPath();

        String s = jsonPath.getJsonObject("category[0]").toString();
        Assert.assertEquals(prodCategory,s);
    }

// Invoking a POST method
@Given("I invoke the POST Product endpoint {string}")
public void i_invoke_the_post_product_endpoint(String endpoint) {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = RestAssured.
                given();

        response =httpRequest.get(endpoint);

        JSONObject requestParams = new JSONObject();
        requestParams.put(  "title", "Slingbacks");
        requestParams.put(    "price", "0.1");
        requestParams.put(  "description", "ladies shoes with 3 inch heel");
        requestParams.put(  "category", "shoes");
        requestParams.put(  "image", "http://example.com");

        httpRequest.body(requestParams.toJSONString());





//Sample Body
//        {
//            "id": 0,
//                "title": "Slingbacks",
//                "price": 0.1,
//                ,
//                ,
//
//        }

//Sample Response
//        {
//            "id": 21,
//                "title": "Slingbacks",
//                "price": 0.1,
//                "description": "ladies shoes with 3 inch heel",
//                "image": "http://example.com",
//                "category": "shoes"
//        }

    }

    @Given("I pass the Request title and body")
    public void i_pass_the_request_title_and_body() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("Post product endpoint")
    public void post_product_endpoint() {

        JSONObject requestParams = new JSONObject();
        requestParams.put(  "title", "Slingbacks");
        requestParams.put(    "price", "0.1");
        requestParams.put(  "description", "ladies shoes with 3 inch heel");
        requestParams.put(  "category", "shoes");
        requestParams.put(  "image", "http://example.com");

        response = RestAssured.given()
                .body(requestParams.toJSONString())
                .post("products");

        Assert.assertEquals(response.getStatusCode(),200);

        JsonPath jsonPath = response.jsonPath();

        // Read the value of Rating of the Nth product - read from the array
        String s = jsonPath.getJsonObject("id").toString();
        Assert.assertEquals(s,21);
    }


}
