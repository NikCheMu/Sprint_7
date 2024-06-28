package requestModels;

public class LoginCurierModel {
    private String login;
    private String password;

    public LoginCurierModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginCurierModel() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LoginCurierModel setLogin(String login) {
        this.login = login;
        return this;
    }

    public LoginCurierModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
