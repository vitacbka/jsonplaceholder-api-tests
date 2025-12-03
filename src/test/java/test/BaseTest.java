package test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();

        RestAssured.requestSpecification = requestSpec;
    }
}
