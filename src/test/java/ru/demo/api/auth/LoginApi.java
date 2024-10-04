package ru.demo.api.auth;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.demo.data.endpoints.LoginEndpoint;
import ru.demo.config.App;

import static io.restassured.RestAssured.given;
import static ru.demo.specs.BaseSpec.*;

public class LoginApi {
    public static String startSession() {
        return
                given(defaultRequestSpec)
                        .contentType(ContentType.URLENC)
                        .formParam("username", App.appConfig.username())
                        .formParam("password", App.appConfig.password())
                        .when()
                        .post(LoginEndpoint.POST)
                        .then()
                        .spec(defaultResponseSpec)
                        .extract().response()
                        .getCookie("JSESSIONID");
    }
}
