package assertions;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.*;

public class Status {

    @Step("Validate status code is 200 OK")
    public static void assertOk(Response response){
        response.then().statusCode(SC_OK);
    }

    @Step("Validate status code is 201 CREATED")
    public static void assertCreated(Response response){
        response.then().statusCode(SC_CREATED);
    }

    @Step("Validate status code is 404 NOTFOUND")
    public static void assertNotFound(Response response){
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Step("Validate status code is 400 BADREQUEST")
    public static void assertBadRequest(Response response){
        response.then().statusCode(SC_BAD_REQUEST);
    }

    @Step("Validate status code is 409 CONFLICT")
    public static void assertConflict(Response response){
        response.then().statusCode(SC_CONFLICT);
    }




}
