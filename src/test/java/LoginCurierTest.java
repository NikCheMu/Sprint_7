import assertions.Status;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import requestModels.LoginCurierModel;
import responseModels.LogInCurierPositiveResponse;

public class LoginCurierTest extends BaseTest{


    public LoginCurierModel alreadyRegisteredCurierLogin;


    @Before
    public void setUp() {
        ///Тут по хорошему нужно из бд получить ранее зарегистрированного пользователя(бэкап с прода к прим.)
        ///Но на тесте нету бд, поэтому вне проекта был зарегистрирован пользователь
        alreadyRegisteredCurierLogin = Helpers.getAlreadyRegisteredCurierFromDb();
    }

    @Test
    public void succesfullLoginReturns200AndId(){
        Response logInResponse = ApiClient.postLoginCurier(alreadyRegisteredCurierLogin,specification.defaultSpecification());
        Status.assertOk(logInResponse);

        LogInCurierPositiveResponse logInCurierPositiveResponse = (LogInCurierPositiveResponse) assertions.Response.deserialize(logInResponse,LogInCurierPositiveResponse.class);
        assertions.Response.assertField(logInCurierPositiveResponse.getId());
        assertions.Response.assertField(logInCurierPositiveResponse.getId(),339015,true);
    }
}
