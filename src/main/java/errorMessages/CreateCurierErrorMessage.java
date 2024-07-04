package errorMessages;

public enum CreateCurierErrorMessage {
    NOTENOUGHDATAPROVIDED("Недостаточно данных для создания учетной записи"),
    USERALREADYREGISTERED("Этот логин уже используется");

    private String message;

    CreateCurierErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

