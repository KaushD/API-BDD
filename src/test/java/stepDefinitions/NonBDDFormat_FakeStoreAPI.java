package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class NonBDDFormat_FakeStoreAPI {
    @Test
    public void getAPI() {
        given()
        .when()
                .get("https://fakestoreapi.com/products")
        .then()
                .statusCode(200);
    }

    @Test
    public void postAPI() {
//        HashMap requestParams = new HashMap<>();
//        requestParams.put("title", "Slingbacks");
//        requestParams.put("price", "0.1");
//        requestParams.put("description", "ladies shoes with 3 inch heel");
//        requestParams.put("category", "shoes");
//        requestParams.put("image", "http://example.com");

        JSONObject requestParams = new JSONObject();
        requestParams.put(  "title", "Slingbacks");
        requestParams.put(    "price", "0.1");
        requestParams.put(  "description", "ladies shoes with 3 inch heel");
        requestParams.put(  "category", "shoes");
        requestParams.put(  "image", "http://example.com");


        Response response =
                given()
                        .contentType("application/json")
                      //  .body(requestParams)
                        .body(requestParams.toJSONString())
                .when()
                        .post("https://fakestoreapi.com/products")
                .then()
                        .statusCode(200)
                        .body(
                                "title", equalTo("Slingbacks"),
                                "id",equalTo(21)
                        )
                        .log().body().extract().response();

        /* using JSONPath is better than asString
        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("Record"),"");
        */
//
//        // JSON Representation of the Response
//        JsonPath jsonPath = response.jsonPath();
//      //  Object jsonObject = jsonPath.get("title");
////        int id = Integer.parseInt(jsonPath.getJsonObject("id").toString());
////        Assert.assertEquals(id,21);
//
////        // Read the value of Rating of the Nth product - read from the array
//        String s = jsonPath.get(".title");
//        Assert.assertEquals(s, "Slingbacks");
//        System.out.println("response title : "+s);
    }

    @Test
    public void putAPI() {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"title\": \"Updated Slingbacks\"}")
                .when()
                .put("/products/21")
                .then()
                .statusCode(200)
                .body("id", equalTo(21))
                .body("title", equalTo("Updated Slingbacks"))
                .extract().response();
    }

    @Test
    public void validateDeleteResponse() {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response = given()
                .when()
                .delete("/products/21")
                .then()
                .statusCode(200)
                .body("id", equalTo(21))
                .extract().response();
    }
}
