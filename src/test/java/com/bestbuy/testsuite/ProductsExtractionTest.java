package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }


    //1. Extract the limit
    @Test
    public void test01() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //   2. Extract the total
    @Test
    public void test02() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //    3. Extract the name of 5th product
    @Test
    public void test03() {
        String fifthproduct = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + fifthproduct);
        System.out.println("------------------End of Test---------------------------");


    }

    //    4. Extract the names of all the products
    @Test
    public void test04() {
        List<String> names = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all the products : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //    5. Extract the productId of all the products
    @Test
    public void test05() {
        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ProductIds of all the products is : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //    6. Print the size of the data list
    @Test
    public void test06() {
        List<String> data = response.extract().path("data");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of all data is : " + data.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //    7. Get all the value/data of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test07() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //    8. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test08() {
        String model = response.extract().path("data.find{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model of the Energizer - N Cell E90 Batteries (2-Pack) is : " + model);
        System.out.println("------------------End of Test---------------------------");
    }

    //    9. Get all the categories of 8th products
    @Test
    public void test09() {
        List<Map<String, ?>> categories = response.extract().path("data[7].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all categories of 8th product : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //    10. Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<Map<String, ?>> categories1 = response.extract().path("data[2].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all categories of id 150115: " + categories1);
        System.out.println("------------------End of Test---------------------------");
    }

    //    11. Get all the descriptions of all the products
    @Test
    public void test011() {
        List<Map<String, ?>> descriptions = response.extract().path("data.description");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all products are : " + descriptions);
        System.out.println("------------------End of Test---------------------------");
    }

    //    12. Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<Integer> ids = response.extract().path("data.categories.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Category IDs of all products: " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    // 13. Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<String> type = response.extract().path("data.type");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The type of all products is: " + type);
        System.out.println("------------------End of Test---------------------------");
    }

    // 14. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {
        int totalCategories = response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories.size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Total categories for 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)': " + totalCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //    15. Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List<String> createdAt = response.extract().path("data.findAll { it.price < 5.49 }.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Created at for products priced less than $5.49: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //    16. Find the name of all categories where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016() {
        List<String> categoryNames = response.extract().path("data.find { it.name == 'Energizer - MAX Batteries AA (4-Pack)' }.categories.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Category names for 'Energizer - MAX Batteries AA (4-Pack)': " + categoryNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //    17. Find the manufacturer of all the products
    @Test
    public void test017() {
        List<String> manufacturers = response.extract().path("data.manufacturer");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Manufacturers of all products: " + manufacturers);
        System.out.println("------------------End of Test---------------------------");
    }

    //    18. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test018() {
        List<String> images = response.extract().path("data.findAll { it.manufacturer == 'Energizer' }.image");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Images for products manufactured by 'Energizer': " + images);
        System.out.println("------------------End of Test---------------------------");
    }

    //    19. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019() {
        List<String> createdAt = response.extract().path("data.findAll { it.price > 5.99 }.categories.createdAt.flatten()");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Created at for categories of products priced more than $5.99: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //    20. Find the uri of all the products
    @Test
    public void test020() {
        List<String> uris = response.extract().path("data.url");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("URIs of all products: " + uris);
        System.out.println("------------------End of Test---------------------------");
    }
}
