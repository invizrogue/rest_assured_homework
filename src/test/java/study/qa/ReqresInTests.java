package study.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ReqresInTests {

    String BASE_URL = "https://reqres.in";

    @Test
    @DisplayName("Проверка, что количество ресурсов на странице меньше или равно шести")
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
    @DisplayName("Проверка, что регистрация успешна и выданный токен содержит строку" +
            "из букв или цифр длиной 17 символов")
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
    @DisplayName("Проверка, что необъявленные раннее пользователи регистрироваться не могут")
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
    @DisplayName("Проверка кода ответа 204 при удалении пользователя")
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
    @DisplayName("Проверка, что id в строке запроса равен id пользователя в теле ответа")
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
    @DisplayName("Проверка, что нет ресурса с id=0")
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
