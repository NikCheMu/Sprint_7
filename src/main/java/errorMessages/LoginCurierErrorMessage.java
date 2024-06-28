package errorMessages;

public enum LoginCurierErrorMessage {
    NOTENOUGHDATAPROVIDED("Недостаточно данных для входа"),
    USERNOTFOUND("Учетная запись не найдена");

    private String message;

    LoginCurierErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
