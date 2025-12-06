package test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@Epic("JSON Placeholder API")
@Feature("Posts Operations")
public class JsonPlaceHolderApiTests extends BaseTest {

    @Test
    @Story("Get Post")
    @DisplayName("Get /posts/1 - get id post")
    void getPostByIdTest() {
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
    @Story("Get all posts")
    @DisplayName("Get /posts - get all posts")
    public void getAllPostTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(100));
    }

    @Test
    @Story("Create post")
    @DisplayName("POST /posts - create new post")
    public void createPostTest() {
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
    @Story("Update post")
    @DisplayName("PUT /posts/1 - full update post")
    public void updatePostTest() {
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
    @Story("Patch post")
    @DisplayName("PATCH /posts/1 - partial update post")
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
    @Story("Delete Post")
    @DisplayName("DELETE /posts/1 — удаление поста")
    public void deletePostTest() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200)
                .body(is("{}"));
    }


    @Test
    @Story("Filter post")
    @DisplayName("GET /posts?userId=1 - filter post by userId")
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
    @Story("Get comments")
    @DisplayName("GET /posts/1/comments - get comments for post")
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