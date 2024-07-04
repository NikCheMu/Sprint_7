import assertions.Status;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requestModels.CreateOrderModel;
import responseModels.CreateOrderPositiveResponse;

@RunWith(Parameterized.class)
public class CreateOrderDifferentColorTest extends BaseTest{

    private final CreateOrderModel orderModel;

    private static final String[] noColor = new String[]{};

    private static final String[] grey = new String[]{"GREY"};

    private static final String[] black = new String[]{"BLACK"};

    private static final String[] allColours = new String[]{"BLACK","GREY"};



    public CreateOrderDifferentColorTest(CreateOrderModel orderModel) {
        this.orderModel = orderModel;

    }


    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {Helpers.getRandomOrderModelWithColour(black)},
                {Helpers.getRandomOrderModelWithColour(grey)},
                {Helpers.getRandomOrderModelWithColour(allColours)},
                {Helpers.getRandomOrderModelWithColour(noColor)}

        };
    }

    @Test
    @DisplayName("Check different colours order creation")
    public void successOrderCreatedReturn201AndTrackNumber(){
        Response response =  ApiClient.postOrder(orderModel,specification.defaultSpecification());
        Status.assertCreated(response);
        CreateOrderPositiveResponse createOrderPositiveResponse = (CreateOrderPositiveResponse) assertions.Response.deserialize(response, CreateOrderPositiveResponse.class);
        assertions.Response.assertField(createOrderPositiveResponse.getTrack());
    }

}
