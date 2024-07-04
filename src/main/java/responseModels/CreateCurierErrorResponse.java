package responseModels;

public class CreateCurierErrorResponse {

    private String message;

    public CreateCurierErrorResponse(String message) {
        this.message = message;
    }

    public CreateCurierErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
