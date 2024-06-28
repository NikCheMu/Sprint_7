import assertions.Status;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import queryParams.GetOrdersQueryParams;
import responseModels.orderList.Orders;

@RunWith(Parameterized.class)
public class GetOrdersQueryParamsTest {

    public RequestSpecification params;



    public GetOrdersQueryParamsTest(RequestSpecification params) {
            this.params = params;
    }


    @Parameterized.Parameters
    public static Object[][] testData() {
        int stationNumber = Helpers.getRandomMetroStation();
        String[] metro = {String.valueOf(stationNumber)};

        RequestSpecification curierId = new BaseTest().specification.defaultSpecification();
        GetOrdersQueryParams.getCurierIdQueryParam(curierId,Helpers.getAlreadyRegisteredCurierId());

        RequestSpecification nearestStation = new BaseTest().specification.defaultSpecification();
        GetOrdersQueryParams.getNearestStationdQueryParam(nearestStation,metro);

        RequestSpecification limit = new BaseTest().specification.defaultSpecification();
        GetOrdersQueryParams.getLimitQueryParam(limit,Helpers.getRandInt(1,30));

        RequestSpecification page = new BaseTest().specification.defaultSpecification();
        GetOrdersQueryParams.gePageQueryParam(page,Helpers.getRandInt(1,30));

        RequestSpecification allParams = new BaseTest().specification.defaultSpecification();
        GetOrdersQueryParams.getCurierIdQueryParam(allParams,Helpers.getAlreadyRegisteredCurierId());
        GetOrdersQueryParams.getNearestStationdQueryParam(allParams,metro);
        GetOrdersQueryParams.getLimitQueryParam(allParams,Helpers.getRandInt(1,30));
        GetOrdersQueryParams.gePageQueryParam(allParams,Helpers.getRandInt(1,30));


        return new Object[][]{
                {curierId},
                {nearestStation},
                {limit},
                {page},
                {allParams}
        };

        }
    @Test
    public void differentQueryParamsReturnsListOfOrders(){
        Response response = ApiClient.getOrdersList(params);

        Status.assertOk(response);
        Orders orders = (Orders) assertions.Response.deserialize(response, Orders.class);
        assertions.Response.assertField(orders.getOrders());

    }
}
