package com.bestbuy.crudtest;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBase {

    static String name = "Duracell Batteries" + TestUtils.getRandomValue();
    static String type = "Super";
    static double price = 6.87;
    static String upc = "07567657";
    static String manufacturer = "Duracell";
    static String description = "Compatible with select electronic devices";
    static String model = "MN2400KHG";
    static int ProductId = 0;

    @Test
    public void test001_createStore() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setManufacturer(manufacturer);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        ValidatableResponse response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .post(EndPoints.GET_PRODUCTS)
                .then().log().ifValidationFails().statusCode(201);
        response.log().all();

        ProductId = response.extract().path("id");
        System.out.println("product id is : " + ProductId);

    }

    @Test
    public void test002_ReadStore() {
        int ProductId = 9999679;
        ValidatableResponse response = given().log().ifValidationFails()
                .pathParam("ProductId", ProductId)
                .when()
                .get(EndPoints.GET_PRODUCTS_BY_ID)
                .then().log().ifValidationFails().statusCode(200);
        response.log().all();

        ProductId = response.extract().path("id");
        System.out.println("product id is : " + ProductId);

    }
    @Test
    public void test003_UpdateStore() {

        int ProductId = 9999679;
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "UpdatedName" + TestUtils.getRandomValue());
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setManufacturer(manufacturer);
        productPojo.setDescription(description);
        productPojo.setModel(model);


        ValidatableResponse response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("ProductId", ProductId)
                .when()
                .body(productPojo)
                .put(EndPoints.UPDATE_PRODUCTS_BY_ID)
                .then().log().ifValidationFails().statusCode(200);
        response.log().all();

    }
    @Test
    public void test004_deleteStore() {
        int ProductId = 9999679;

        given().log().ifValidationFails()
                .pathParam("ProductId", ProductId)
                .when()
                .delete(EndPoints.DELETE_PRODUCTS_BY_ID)
                .then()
                .statusCode(200);

        given()
                .log()
                .ifValidationFails()
                .pathParam("ProductId", ProductId)
                .when()
                .get(EndPoints.GET_PRODUCTS_BY_ID)
                .then().log().ifValidationFails().statusCode(404);
    }
}
