package ru.demo.api.objects;

import io.restassured.response.Response;
import ru.demo.api.auth.LoginApi;
import ru.demo.data.endpoints.DictDataEndpoint;
import ru.demo.data.dictionaries.DictType;
import ru.demo.models.objects.CreateObjectRequestModel;

import static io.restassured.RestAssured.given;
import static ru.demo.specs.BaseSpec.*;

public class ObjectsApi {
    private final DictType dictCode = DictType.OBJ;

    public Response createObject(CreateObjectRequestModel object) {
        return
                given(defaultRequestSpec)
                        .cookie("JSESSIONID", LoginApi.startSession())
                        .pathParam("dict-code", dictCode.getCode())
                        .body(object)
                        .when()
                        .post(DictDataEndpoint.CREATE)
                        .then()
                        .spec(defaultResponseSpec)
                        .extract().response();
    }

    public Response deleteObject(String code) {
        return
                given()
                        .spec(defaultRequestSpec)
                        .cookie("JSESSIONID", LoginApi.startSession())
                        .pathParam("dict-code", dictCode.getCode())
                        .pathParam("code", code)
                        .when()
                        .delete(DictDataEndpoint.DELETE)
                        .then()
                        .spec(defaultResponseSpec)
                        .extract().response();
    }
}
