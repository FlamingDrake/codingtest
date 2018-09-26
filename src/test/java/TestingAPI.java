
import hello.MessageController;

import static org.hamcrest.Matchers.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;


public class TestingAPI {

    @Before
    public void init(){
        standaloneSetup(new MessageController());
    }

    @Test
    public void Initial_Test() {
        given().
                when().
                get("/message/status/check").
                then().
                statusCode(200);
    }

    @Test
    public void Test_Empty(){
        given().
                when()
                .get("/message/0")
                .then()
                .statusCode(404);
    }

    @Test
    public void Test_Post_And_Get_Message() {
        given().
                contentType("application/json").
                param("message", "hello").
                when().
                post("/message").
                then().
                statusCode(200);
        given().
                when()
                .get("/message/0")
                .then()
                .statusCode(200)
                .body("id", equalTo(0))
                .body("message", equalTo("hello"));
    }

    @Test
    public void Test_Post_Message() {

        given().
                contentType("application/json").
                param("message", "great").
                when().
                post("/message").
                then().
                statusCode(200).
                body("message", equalTo("great"));
    }

    @Test
    public void Test_Post_And_Delete_A_Message(){
        given().
                contentType("application/json").
                param("message", "hello").
                when().
                post("/message").
                then().
                statusCode(200);
        given().
                when()
                .get("/message/0")
                .then()
                .statusCode(200)
                .body("id", equalTo(0))
                .body("message", equalTo("hello"));
        given().
                when().
                delete("/message/0").
                then().
                statusCode(200);
        given().
                when()
                .get("/message/0")
                .then()
                .statusCode(404);
    }

    @Test
    public void Test_Updating_Message(){
        given().
                contentType("application/json").
                param("message", "hello").
                when().
                post("/message").
                then().
                statusCode(200);
        given().
                contentType("application/json").
                param("message", "world").
                when().
                put("/message/0").
                then().
                statusCode(200);
        given().
                when()
                .get("/message/0")
                .then()
                .statusCode(200)
                .body("message", equalTo("world"));
    }
}