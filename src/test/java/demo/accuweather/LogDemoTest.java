package demo.accuweather;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирование логирования")
public class LogDemoTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Тест логирования запроса и параметров")
    @Description("Данный тест предназначен для тестирования логирования запроса и параметров")
    @Link("https://developer.accuweather.com/apis")
    @Severity(SeverityLevel.MINOR)
    @Story("Получение логов запроса и параметров")
    @Owner("Елена А")
    void logRequestTest() {
        given().log().parameters().log().method()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("top", 50)
                .when()
                .get(getBaseUrl()+"/locations/{version}/topcities/{top}")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Тест логирование ответа")
    @Description("Данный тест предназначен для тестирования логирования ответа")
    @Link("https://developer.accuweather.com/apis")
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение логов ответа")
    @Owner("Елена А")
    void logResponseTest() {
        given().log().parameters()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("top", 50)
                .when()
                .get(getBaseUrl()+"/locations/{version}/topcities/{top}")
                .then().log().body()
                .statusCode(200);
    }

    @Test
    @Disabled
    void logFailTest() {
        given().log().parameters()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("top", 50)
                .when()
                .get(getBaseUrl()+"/locations/{version}/topcities/{top}")
                .then()
                .statusCode(400);
    }
}
