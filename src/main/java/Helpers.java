import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import requestModels.CreateCurierModel;
import requestModels.CreateOrderModel;
import requestModels.LoginCurierModel;
import responseModels.LogInCurierPositiveResponse;
import responseModels.MetroStationPositiveResponse;

import java.time.LocalDate;
import java.util.Random;

public class Helpers {


    static CreateCurierModel getRandomCurier() {
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String login = name + faker.number().randomDigitNotZero();
        String password = login + "@!";

        return new CreateCurierModel(login, password, name);
    }

    static CreateOrderModel getRandomOrderModelWithColour(String[] colour) {
        Faker faker = new Faker();
        LocalDate date = LocalDate.now().plusDays(2);
        return new CreateOrderModel(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().streetAddress(),
                getRandomMetroStation(),
                faker.phoneNumber().cellPhone(),
                faker.number().numberBetween(1, 10),
                date.toString(),
                faker.lorem().characters(10, 50),
                colour);
    }

    static int getRandomMetroStation() {
        Response response = ApiClient.getAllNearestStation(new DefaultSpecification("https://qa-scooter.praktikum-services.ru").defaultSpecification());
        response.then().statusCode(200);
        MetroStationPositiveResponse[] stations = response.as(MetroStationPositiveResponse[].class);
        int maxIndex = stations.length - 1;
        Random rand = new Random();
        int randomNum = rand.nextInt((maxIndex) + 1);;
        return Integer.parseInt(stations[randomNum].getNumber());

    }

    static String getRandomString() {
        Faker faker = new Faker();
        return faker.rickAndMorty().toString();
    }

     static int getRandInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    static LoginCurierModel getAlreadyRegisteredCurierFromDb() {
        CreateCurierModel alreadyRegisteredCurier = new CreateCurierModel("Already", "Registered", "User");
        return new LoginCurierModel(alreadyRegisteredCurier.getLogin(), alreadyRegisteredCurier.getPassword());
    }

    static int getAlreadyRegisteredCurierId() {
        Response response = ApiClient.postLoginCurier(getAlreadyRegisteredCurierFromDb(), new DefaultSpecification("https://qa-scooter.praktikum-services.ru").defaultSpecification());
        return response.body().as(LogInCurierPositiveResponse.class).getId();
    }

    static void tearDown(LoginCurierModel createdCurier, RequestSpecification specification) {
        int curierId = ApiClient.postLoginCurier(createdCurier, specification).body().as(LogInCurierPositiveResponse.class).getId();
        ApiClient.deleteCurier(specification, curierId).then().statusCode(200);
    }

    static Object deserialize(Response response, Class model){
        return response.body().as(model);
    }

    public static void main(String[] args){
        Response response = ApiClient.postLoginCurier(getAlreadyRegisteredCurierFromDb(), new DefaultSpecification("https://qa-scooter.praktikum-services.ru").defaultSpecification());
        LogInCurierPositiveResponse a = (LogInCurierPositiveResponse) deserialize(response,LogInCurierPositiveResponse.class);
        System.out.println("1111");
    }



}


