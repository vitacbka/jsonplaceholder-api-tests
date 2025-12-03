package test;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JsonPlaceHolderApiTests extends BaseTest {

    @Test
    void testGetPostByIdTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", not(emptyString()))
                .body("body", not(emptyString()));
    }
}