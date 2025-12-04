package test;

import models.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @Test
    public void testGetAllPostTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(100));
    }

    @Test
    public void testCreatePost() {
        Post newPost = Post.builder()
                .title("foo")
                .body("bar")
                .userId(1)
                .build();
        given()
                .spec(requestSpec)
                .body(newPost)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("id", equalTo(101))
                .body("title", equalTo(newPost.getTitle()))
                .body("body", equalTo(newPost.getBody()))
                .body("userId", equalTo(newPost.getUserId()));
    }

    @Test
    public void testUpdatePost() {
        Post updatedPost = Post.builder()
                .id(1)
                .title("updated title")
                .body("updated body")
                .userId(1)
                .build();

        given()
                .spec(requestSpec)
                .body(updatedPost)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo(updatedPost.getTitle()))
                .body("body", equalTo(updatedPost.getBody()));
    }

    @Test
    void patchPostTest() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200)
                .body(is("{}"));
    }

    @Test
        public void filterPostsByUserIdTest() {
            given()
                    .spec(requestSpec)
                    .queryParam("userId", 1)
                    .when()
                    .get("/posts")
                    .then()
                    .statusCode(200)
                    .body("$.size()", equalTo(10))
                    .body("userId", everyItem(equalTo(1)));
    }

    @Test
    void getCommentsForPostTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1/comments")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThan(0))
                .body("[0].postId", equalTo(1));
    }
}