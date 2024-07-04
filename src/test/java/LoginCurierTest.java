import assertions.Status;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import requestModels.LoginCurierModel;
import responseModels.LogInCurierPositiveResponse;

public class LoginCurierTest extends BaseTest{


    public LoginCurierModel alreadyRegisteredCurierLogin;


    @Before
    @Step("Prepare data for tests")
    public void setUp() {
        ///Тут по хорошему нужно из бд получить ранее зарегистрированного пользователя(бэкап с прода к прим.)
        ///Но на тесте нету бд, поэтому вне проекта был зарегистрирован пользователь
        alreadyRegisteredCurierLogin = Helpers.getAlreadyRegisteredCurierFromDb();
    }

    @Test
    @DisplayName("Check already registered user can login")
    public void succesfullLoginReturns200AndId(){
        Response logInResponse = ApiClient.postLoginCurier(alreadyRegisteredCurierLogin,specification.defaultSpecification());
        Status.assertOk(logInResponse);

        LogInCurierPositiveResponse logInCurierPositiveResponse = (LogInCurierPositiveResponse) assertions.Response.deserialize(logInResponse,LogInCurierPositiveResponse.class);
        assertions.Response.assertField(logInCurierPositiveResponse.getId());
        assertions.Response.assertField(logInCurierPositiveResponse.getId(),339015,true);
    }
}
