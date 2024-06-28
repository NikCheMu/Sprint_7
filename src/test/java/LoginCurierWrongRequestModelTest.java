import assertions.Status;
import errorMessages.LoginCurierErrorMessage;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requestModels.LoginCurierModel;
import responseModels.LoginCurierErrorResponse;

@RunWith(Parameterized.class)
public class LoginCurierWrongRequestModelTest extends BaseTest{
    private final LoginCurierModel requestModel;


    public LoginCurierWrongRequestModelTest(LoginCurierModel requestModel) {


        this.requestModel = requestModel;
    }



    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {Helpers.getAlreadyRegisteredCurierFromDb().setLogin(null)},
                {Helpers.getAlreadyRegisteredCurierFromDb().setPassword(null) },

        };
    }

    @Test
    public void loginAndPasswordAreRequired() {
        Response logInResponse = ApiClient.postLoginCurier(requestModel,specification.defaultSpecification());
        Status.assertBadRequest(logInResponse);
        LoginCurierErrorResponse errorResponse = (LoginCurierErrorResponse) assertions.Response.deserialize(logInResponse, LoginCurierErrorResponse.class);
        assertions.Response.assertField(errorResponse.getMessage(),LoginCurierErrorMessage.NOTENOUGHDATAPROVIDED.getMessage(),true);
    }
}
