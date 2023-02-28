package study.qa;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ReqresInTests {

    String BASE_URL = "https://reqres.in";

    @Test
    void checkCountItemsPerPageInResourcesListTest() {
        given()
                .log().uri()
                .when()
                .baseUri(BASE_URL)
                .get(Endpoints.getListResource)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data", hasSize(lessThanOrEqualTo(6)));
    }

    @Test
    void checkSuccessfulRegisterTest() {
        String testData = "{\"email\":\"eve.holt@reqres.in\", \"password\":\"pistol\"}";

        given()
                .log().uri()
                .when()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .body(testData)
                .post(Endpoints.postRegister)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", matchesPattern("\\w{17}"));
    }

    @Test
    void checkUndefinedUserRegisterTest() {
        String testData = "{\"email\":\"test@test.tt\", \"password\":\"pa$$w0rd\"}";

        given()
                .log().uri()
                .when()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .body(testData)
                .post(Endpoints.postRegister)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Note: Only defined users succeed registration"));
    }

    @Test
    void checkDeleteUserTest() {
        given()
                .log().uri()
                .when()
                .baseUri(BASE_URL)
                .delete(Endpoints.deleteDeleteN + "3")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void checkSingleResourceTest() {
        int testId = 3;

        given()
                .log().uri()
                .when()
                .baseUri(BASE_URL)
                .get(Endpoints.getSingleResourceN + testId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(testId));
    }

    @Test
    void checkNotFoundResourceTest() {
        given()
                .log().uri()
                .when()
                .baseUri(BASE_URL)
                .get(Endpoints.getSingleResourceN + 0)
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }
}
