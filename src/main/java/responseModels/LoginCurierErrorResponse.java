package responseModels;

public class LoginCurierErrorResponse {
    private String message;

    public LoginCurierErrorResponse(String message) {
        this.message = message;
    }

    public LoginCurierErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
