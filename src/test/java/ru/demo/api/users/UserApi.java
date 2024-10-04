package ru.demo.api.users;

import ru.demo.api.auth.LoginApi;
import ru.demo.data.endpoints.UsersEndpoint;
import ru.demo.models.users.UserResponseModel;

import static io.restassured.RestAssured.given;
import static ru.demo.specs.BaseSpec.defaultRequestSpec;
import static ru.demo.specs.BaseSpec.defaultResponseSpec;

public class UserApi {
    public static UserResponseModel getAuthorizedUserInfo() {
        return
                given(defaultRequestSpec)
                        .cookie("JSESSIONID", LoginApi.startSession())
                        .when()
                        .get(UsersEndpoint.GET_LOGGED)
                        .then()
                        .spec(defaultResponseSpec)
                        .extract().as(UserResponseModel.class);
    }
}
