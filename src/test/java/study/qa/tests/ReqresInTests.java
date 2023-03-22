package study.qa.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import study.qa.model.RegisterRequestModel;
import study.qa.model.RegisterResponseModel;
import study.qa.model.ResourceDataModel;
import study.qa.model.ResourceListResponseModel;
import study.qa.specs.Endpoints;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static study.qa.specs.ReqresSpec.*;

public class ReqresInTests {

    @Test
    @DisplayName("Проверка, что количество ресурсов на странице меньше или равно шести")
    void checkCountItemsPerPageInResourcesListTest() {
        ResourceListResponseModel response =
                step("Запрос на загрузку списка ресурсов", () ->

                        given(commonRequestSpec)
                                .when()
                                .get(Endpoints.getListResource)
                                .then()
                                .spec(commonResponseSpec)
                                .extract().as(ResourceListResponseModel.class));
        step("Проверка, что размер выводимого списка меньше или равен 6", () ->
                assertThat(response.getData()).hasSizeLessThanOrEqualTo(6));
    }

    @Test
    @DisplayName("Проверка, что регистрация успешна и выданный токен содержит строку" +
            "из букв или цифр длиной 17 символов")
    void checkSuccessfulRegisterTest() {
        RegisterRequestModel request = new RegisterRequestModel();
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");

        RegisterResponseModel response =
                step("Запрос на регистрацию пользователя", () ->
                        given(commonRequestSpec)
                                .when()
                                .body(request)
                                .post(Endpoints.postRegister)
                                .then()
                                .spec(commonResponseSpec)
                                .extract().as(RegisterResponseModel.class));

        step("Проверка выданного токена", () ->
                assertThat(response.getToken()).matches("\\w{17}"));
    }

    @Test
    @DisplayName("Проверка, что необъявленные раннее пользователи регистрироваться не могут")
    void checkUndefinedUserRegisterTest() {
        RegisterRequestModel request = new RegisterRequestModel();
        request.setEmail("test@test.tt");
        request.setPassword("pa$$w0rd");

        RegisterResponseModel response =
                step("Запрос на регистрацию пользователя", () ->
                        given(commonRequestSpec)
                                .when()
                                .body(request)
                                .post(Endpoints.postRegister)
                                .then()
                                .spec(badrequestResponseSpec)
                                .extract().as(RegisterResponseModel.class));

        step("Проверка сообщения об ошибке", () ->
                assertThat(response.getError())
                        .isEqualTo("Note: Only defined users succeed registration"));

    }

    @Test
    @DisplayName("Проверка кода ответа 204 при удалении пользователя")
    void checkDeleteUserTest() {

        Integer status =
                step("Запрос на удаление существующего пользователя", () ->
                        given(commonRequestSpec)
                                .when()
                                .delete(Endpoints.deleteDeleteN + 3)
                                .then()
                                .spec(otherResponseSpec)
                                .extract().statusCode());

        step("Проверка, что код ответа равен 204", () ->
                assertThat(status).isEqualTo(204));
    }

    @ValueSource(ints = {1, 3, 6})
    @ParameterizedTest(name = "Проверка, что id={0} в строке запроса равен id пользователя в теле ответа")
    void checkSingleResourceTest(int testId) {

        ResourceDataModel data =
                step("Запрос на получение данных о пользователе с id=" + testId, () ->
                        given(commonRequestSpec)
                                .when()
                                .get(Endpoints.getSingleResourceN + testId)
                                .then()
                                .spec(commonResponseSpec)
                                .extract().body().jsonPath().getObject("data", ResourceDataModel.class));

        step("что id=" + testId + " в строке запроса равен id пользователя в теле ответа", () ->
                assertThat(data.getId()).isEqualTo(testId));
    }

    @Test
    @DisplayName("Проверка, что нет ресурса с id=0")
    void checkNotFoundResourceTest() {
        Integer status =
                step("Запрос на получение информации о ресурсе", () ->
                        given(commonRequestSpec)
                                .when()
                                .get(Endpoints.getSingleResourceN + 0)
                                .then()
                                .spec(otherResponseSpec)
                                .extract().statusCode());

        step("Проверка, что код ответа равен 404", () ->
                assertThat(status).isEqualTo(404));
    }
}
