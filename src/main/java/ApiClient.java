import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import requestModels.CreateCurierModel;
import requestModels.CreateOrderModel;
import requestModels.LoginCurierModel;

import static io.restassured.RestAssured.given;

public class ApiClient {



    @Step("Send POST/api/v1/courier")
    public static Response postCreateCurier(CreateCurierModel createCurierModel,RequestSpecification specification){
        return given()
                .spec(specification)
                .body(createCurierModel)
                .when()
                .post("/api/v1/courier");
//
    }

    @Step("Send POST/api/v1/courier/login")
    public static Response postLoginCurier(LoginCurierModel loginCurierModel,RequestSpecification specification){
        return given()
                .spec(specification)
                .body(loginCurierModel)
                .when()
                .post("/api/v1/courier/login");


    }

    @Step("Send GET/api/v1/orders")
    public static Response getOrdersList(RequestSpecification specification){
        return  given()
                .spec(specification)
                .get("/api/v1/orders");

    }


    @Step("Send DELETE/api/v1/courier")
    public static Response deleteCurier(RequestSpecification specification,int curierId){
        return given()
                .spec(specification)
                .delete("/api/v1/courier/"+curierId);

    }

    @Step("Send GET/api/v1/stations/search")
    public static Response getAllNearestStation(RequestSpecification specification){
        return given()
                .spec(specification)
                .get("/api/v1/stations/search");

    }

    @Step("Send POST/api/v1/orders")
    public static Response postOrder(CreateOrderModel orderModel, RequestSpecification specification){
        return given()
                .spec(specification)
                .body(orderModel)
                .when()
                .post("/api/v1/orders");
    }
}
