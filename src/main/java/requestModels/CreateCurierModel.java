package requestModels;

public class CreateCurierModel {

    private String login;
    private String password;
    private String firstName;

    public CreateCurierModel(String login ,String password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCurierModel(){

    }

    public String getLogin() {
        return login;
    }

    public CreateCurierModel setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateCurierModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CreateCurierModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}
