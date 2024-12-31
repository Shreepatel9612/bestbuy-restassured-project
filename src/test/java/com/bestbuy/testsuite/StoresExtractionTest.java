package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = RestAssured.given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total
    @Test
    public void test002() {

        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {

        String fifthStoreName = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of 5th Store: " + fifthStoreName);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the names of all the store
    @Test
    public void test004() {

        List<String> storeNames = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Names of All Stores: " + storeNames);
        System.out.println("------------------End of Test---------------------------");

    }

    //5. Extract the storeId of all the store
    public void test005() {

        List<Integer> storeIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store IDs : " + storeIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Print the size of the data list
    @Test
    public void test006() {

        int dataSize = response.extract().path("data.size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Size of Data List : " + dataSize);
        System.out.println("------------------End of Test---------------------------");

    }

    //    7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {

        List<HashMap<String, ? >> stCloudStore = response.extract().path("data.findAll{it.name == 'St Cloud'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Details of Store 'St Cloud': " + stCloudStore.get(0));
        System.out.println("------------------End of Test---------------------------");

    }

    //8. Get the address of the store where store name ="Rochester"
    @Test
    public void test008() {

        List<String> rochesterAddress;
        rochesterAddress = response.extract().path("data.findAll { it.name == 'Rochester' }.address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address of 'Rochester':" + rochesterAddress);
        System.out.println("------------------End of Test---------------------------");

    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {

        List<HashMap<String, ? >> eighthStoreServices = response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Services of 8th Store: " + eighthStoreServices);
        System.out.println("------------------End of Test---------------------------");

    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {

        HashMap<String, ? > windowsStoreServices = response.extract().path("data.find { it.services.any { it.name == 'Windows Store' } }.services.find { it.name == 'Windows Store' }.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Storeservices of 'Windows Store': " + windowsStoreServices);
        System.out.println("------------------End of Test---------------------------");

    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {

        List<Integer> allStoreIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Store IDs : " + allStoreIds.get(0));
        System.out.println("------------------End of Test---------------------------");

    }

    //12. Get id of all the store
    @Test
    public void test012() {

        List<Integer> allIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All IDs : " + allIds.get(0));
        System.out.println("------------------End of Test---------------------------");

    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {

        List<String> storesInND = response.extract().path("data.findAll { it.state == 'ND' }.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Stores in ND: " + storesInND.get(0));
        System.out.println("------------------End of Test---------------------------");

    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {

        int rochesterServicesCount = response.extract().path("data.find { it.name == 'Rochester' }.services.size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + rochesterServicesCount);
        System.out.println("------------------End of Test---------------------------");

    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {

        List<String> windowsStoreCreatedAt = response.extract().path("data.services.flatten().findAll { it.name == 'Windows Store' }.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("CreatedAt for 'Windows Store' services: " + windowsStoreCreatedAt);
        System.out.println("------------------End of Test---------------------------");

    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {

        List<String> fargoServices = response.extract().path("data.find { it.name == 'Fargo' }.services.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Services in 'Fargo : "  + fargoServices.get(0));
        System.out.println("------------------End of Test---------------------------");

    }

    //17. Find the zip of all the store
    @Test
    public void test017() {

        List<String> allStoreZips = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zips of All Stores: " + allStoreZips.get(0));
        System.out.println("------------------End of Test---------------------------");

    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {

        String rosevilleZip = response.extract().path("data.find { it.name == 'Roseville' }.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of 'Roseville': " + rosevilleZip);
        System.out.println("------------------End of Test---------------------------");

    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {

        HashMap<String, ?> magnoliaHomeTheaterServices = response.extract().path("data.services.flatten().find { it.name == 'Magnolia Home Theater' }.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Storeservices of 'Magnolia Home Theater': " + magnoliaHomeTheaterServices);
        System.out.println("------------------End of Test---------------------------");

    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {

        List<Double> allLats = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Latitudes of All Stores : " + allLats.get(0));
        System.out.println("------------------End of Test---------------------------");

    }
}
