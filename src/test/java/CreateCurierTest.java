import assertions.Status;
import errorMessages.CreateCurierErrorMessage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requestModels.CreateCurierModel;
import requestModels.LoginCurierModel;
import responseModels.CreateCurierErrorResponse;

import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateCurierTest extends BaseTest {

    public CreateCurierModel curier;
    public LoginCurierModel curierLogin;


    @Before
    @Step("Prepare data for tests")
    public void setUp() {
        curier = Helpers.getRandomCurier();
        curierLogin = new LoginCurierModel(curier.getLogin(),curier.getPassword());
    }

    @After
    @Step("Clean up after tests")
    public void tearDown(){
        Helpers.tearDown(curierLogin,specification.defaultSpecification());
    }



    @Test
    @DisplayName("Check new user can register and then login")
    public void createNewCurierAndLogin(){
        Response createResponse = ApiClient.postCreateCurier(curier, specification.defaultSpecification());
        Status.assertCreated(createResponse);

        Response logInResponse = ApiClient.postLoginCurier(curierLogin,specification.defaultSpecification());
        Status.assertOk(logInResponse);
        logInResponse.then().assertThat().body("id",notNullValue());
    }

    @Test
    @DisplayName("Check user cant register with same login twice")
    public void usersWithSameLoginCantBeCreated(){
        Response successCreateResponse = ApiClient.postCreateCurier(curier, specification.defaultSpecification());
        Status.assertCreated(successCreateResponse);

        Response failedCreateResponse = ApiClient.postCreateCurier(curier, specification.defaultSpecification());
        Status.assertConflict(failedCreateResponse);

        CreateCurierErrorResponse deserialized = (CreateCurierErrorResponse) assertions.Response.deserialize(failedCreateResponse, CreateCurierErrorResponse.class);
        assertions.Response.assertField(deserialized.getMessage(),CreateCurierErrorMessage.USERALREADYREGISTERED.getMessage(),true);
    }

    @Test
    @DisplayName("Check status code should be 201")
    public void succesfullCreateCurierReturns201Created(){
        Response createResponse = ApiClient.postCreateCurier(curier, specification.defaultSpecification());
        Status.assertCreated(createResponse);
    }



}
