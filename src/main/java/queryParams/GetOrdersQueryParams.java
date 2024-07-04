package queryParams;

import io.restassured.specification.RequestSpecification;

public class GetOrdersQueryParams {

    public static void getCurierIdQueryParam(RequestSpecification specification,int id){
        specification.queryParam("courierId",id);
    }

    public static void getNearestStationdQueryParam(RequestSpecification specification,String[] metro){
        specification.queryParam("nearestStation", (Object) metro);
    }

    public static void getLimitQueryParam(RequestSpecification specification,int limit){
        specification.queryParam("limit", limit);
    }

    public static void gePageQueryParam(RequestSpecification specification,int page){
        specification.queryParam("page", page);
    }



}
