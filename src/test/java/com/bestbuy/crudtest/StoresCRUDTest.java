package com.bestbuy.crudtest;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBase {

    static String name = "TestStore" + TestUtils.getRandomValue();
    static String type = "TestType";
    static String address = "123 Test Street";
    static String address2 = "Suite 456";
    static String city = "TestCity";
    static String state = "TestState";
    static String zip = "12345";


    static int storeId = 0;


    // POST (Create)
    @Test
    public void test001_createStore() {


        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);


        ValidatableResponse response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post(EndPoints.GET_STORES)
                .then().log().ifValidationFails().statusCode(201);
        response.log().all();

        storeId = response.extract().path("id");
        System.out.println("store id is : " + storeId);
    }

    @Test
    public void test002_ReadStore() {
        int storeId = 8923;
        ValidatableResponse response = given().log().ifValidationFails()
                .pathParam("StoreId", storeId)
                .when()
                .get(EndPoints.GET_STORES_BY_ID)
                .then().log().ifValidationFails().statusCode(200);
        response.log().all();

        storeId = response.extract().path("id");
        System.out.println("store id is : " + storeId);
    }

    @Test
    public void test003_UpdateStore() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name + "UpdatedName" + TestUtils.getRandomValue());
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);

        int storeId = 8923;


        ValidatableResponse response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("StoreId", storeId)
                .when()
                .body(storePojo)
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then().log().ifValidationFails().statusCode(200);
        response.log().all();
    }

    @Test
    public void test004_deleteStore() {
        int storeId = 8923;
        given().log().ifValidationFails()
                .pathParam("StoreId", storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then().log().all()
                .statusCode(200);

        given()
                .log()
                .ifValidationFails()
                .pathParam("StoreId", storeId)
                .when()
                .get(EndPoints.GET_STORES_BY_ID)
                .then().log().ifValidationFails().statusCode(404);


    }
}

