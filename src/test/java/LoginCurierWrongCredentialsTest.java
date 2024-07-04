import assertions.Status;
import errorMessages.LoginCurierErrorMessage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requestModels.LoginCurierModel;
import responseModels.LoginCurierErrorResponse;

@RunWith(Parameterized.class)
public class LoginCurierWrongCredentialsTest extends BaseTest {
    private final LoginCurierModel requestModel;



    public LoginCurierWrongCredentialsTest(LoginCurierModel requestModel) {
        this.requestModel = requestModel;


    }


    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {Helpers.getAlreadyRegisteredCurierFromDb().setLogin(Helpers.getRandomString())},
                {Helpers.getAlreadyRegisteredCurierFromDb().setPassword(Helpers.getRandomString())},
        };

    }

    @Test
    @DisplayName("Check login with wrong credentials")
    public void loginWithWrongCredentialsReturn404AndErrorMessage(){
        Response logInResponse = ApiClient.postLoginCurier(requestModel,specification.defaultSpecification());
        Status.assertNotFound(logInResponse);
        LoginCurierErrorResponse errorResponse = (LoginCurierErrorResponse) assertions.Response.deserialize(logInResponse, LoginCurierErrorResponse.class);
        assertions.Response.assertField(errorResponse.getMessage(),LoginCurierErrorMessage.USERNOTFOUND.getMessage(),true);
    }
}

