import assertions.Status;
import errorMessages.CreateCurierErrorMessage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requestModels.CreateCurierModel;
import responseModels.CreateCurierErrorResponse;

@RunWith(Parameterized.class)
 public class CreateCurierParameterizedTest extends BaseTest {

    private final CreateCurierModel curierModel;

    private final String errorMessage;

    public CreateCurierParameterizedTest(CreateCurierModel curierModel, String errorMessage) {
        this.curierModel = curierModel;
        this.errorMessage = errorMessage;
    }


    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {Helpers.getRandomCurier().setLogin(null), CreateCurierErrorMessage.NOTENOUGHDATAPROVIDED.getMessage()},
                {Helpers.getRandomCurier().setPassword(null), CreateCurierErrorMessage.NOTENOUGHDATAPROVIDED.getMessage()},

        };
    }

    @Test
    @DisplayName("Check login and password are required at registration")
    public void loginAndPasswordAreRequired(){
        Response createResponse = ApiClient.postCreateCurier(curierModel, specification.defaultSpecification());
        Status.assertBadRequest(createResponse);
        CreateCurierErrorResponse deserialized = (CreateCurierErrorResponse) assertions.Response.deserialize(createResponse, CreateCurierErrorResponse.class);
        assertions.Response.assertField(deserialized.getMessage(),errorMessage,true);
    }
}
