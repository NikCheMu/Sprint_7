import assertions.Status;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import responseModels.orderList.Order;
import responseModels.orderList.Orders;
import responseModels.orderList.PageInfo;


public class GetOrdersListTest extends BaseTest{

    public Order[] orderList;

    @Before
    @Step("Prepare data for tests")
    public void setUp() {
        orderList = new Order[0];
    }

    @Test
    @DisplayName("Check list of orders presented in response with no query params")
    public void getOrdersListReturnListOfOrders(){
        Response response = ApiClient.getOrdersList(specification.defaultSpecification());

        Status.assertOk(response);
        Orders orders = (Orders) assertions.Response.deserialize(response, Orders.class);
        PageInfo pageInfo = orders.getPageInfo();
        assertions.Response.assertField(orders.getOrders());
        assertions.Response.assertField(orders.getOrders(),orderList,false);
        assertions.Response.assertField(pageInfo);
        assertions.Response.assertField(pageInfo.getLimit(),30,true);
        assertions.Response.assertField(pageInfo.getPage(),0,true);


    }
}
